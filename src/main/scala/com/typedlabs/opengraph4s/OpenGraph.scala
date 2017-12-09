package com.typedlabs.opengraph4s

import cats.implicits._

import org.jsoup.Jsoup
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

case class OpenGraph(  
  `type`: Option[String] = None,
  url: Option[String] = None,
  title: Option[String] = None,
  description: Option[String] = None,
  determiner: Option[String] = None,
  locale: Option[String] = None,
  localesAlternate: List[String] = Nil,
  siteName: Option[String] = None,
  images: List[Image] = Nil,  
  videos: List[Video] = Nil,
  audios: List[Audio] = Nil
)

object OpenGraph {
  
  type OpenGraphProperties = Map[String, List[String]]
  type MediaConverter[A] = (String, Int) => A

  /** Extract open graph data
  *  @param url the url from wich to extract the opengraph data
  */
  def extract(url: String)(implicit ctx: ExecutionContext):Future[Either[OpenGraphError, OpenGraph]] = Future {

    val props: OpenGraphProperties = 
      Jsoup.connect(url).get().select("[property]")
        .asScala
        .toList
        .foldLeft(Map.empty[String, List[String]]){ (m, element) =>

          val maybeProperty = Option(element.attr("property"))
          val maybeContent = Option(element.attr("content"))
          val entry =
            for {
              property <- maybeProperty
              content <- maybeContent
            } yield (property, content)

          // Use semigroup append here with Map[String, List[_]] to not loose data (Eg. og:locale:alternate)
          entry.fold(m) { case (k, v) => m combine Map(k -> List(v)) }

        }
    if(props.isEmpty){
      Left(OpenGraphNotFound)
    } else {
      val og = 
        OpenGraph(
          `type` = props.get("og:type").map(_.mkString),
          url = props.get("og:url").map(_.mkString),
          title = props.get("og:title").map(_.mkString),
          description = props.get("og:description").map(_.mkString),
          determiner = props.get("og:determiner").map(_.mkString),
          locale = props.get("og:locale").map(_.mkString),
          siteName = props.get("og:site_name").map(_.mkString),
          localesAlternate = props.get("og:locale:alternate").getOrElse(Nil),
          images = extractImages(props),
          videos = extractVideos(props),
          audios = extractAudios(props)
        )        
      Right(og)
    }

  }

  /** Extract opengraph images
  *  @param tags the opengraph properites extracted from the document
  *  @param block the media converter function
  */
  private def extractMedia[A](tags: Option[List[String]])(block: MediaConverter[A]): List[A] =
    tags
      .map(_.zipWithIndex.map{ case (url, i) => block(url, i) })
      .getOrElse(Nil)

  /** Extract opengraph images
  *  @param props the opengraph properites extracted from the document
  */
  private def extractImages(props: OpenGraphProperties): List[Image] = 
      extractMedia[Image](props.get("og:image")){
        case (url, i) => 
          Image(
            url,
            `type` = props.get("og:image:type").flatMap(_.lift(i)),
            secureUrl = props.get("og:image:secure_url").flatMap(_.lift(i)),
            width = props.get("og:image:width").flatMap(_.lift(i)),
            height = props.get("og:image:height").flatMap(_.lift(i))
          )
      }

  /** Extract opengraph videos
  *  @param props the opengraph properites extracted from the document
  */
  private def extractVideos(props: OpenGraphProperties): List[Video] = 
      extractMedia[Video](props.get("og:video")){
        case (url, i) => 
            Video(
              url,
              `type` = props.get("og:video:type").flatMap(_.lift(i)),
              secureUrl = props.get("og:video:secure_url").flatMap(_.lift(i)),
              width = props.get("og:video:width").flatMap(_.lift(i)),
              height = props.get("og:video:height").flatMap(_.lift(i)),
              tags = props.get("og:video:tag").getOrElse(Nil)
            )
      }

  /** Extract opengraph audio
  *  @param props the opengraph properites extracted from the document
  */
  private def extractAudios(props: Map[String, List[String]]): List[Audio] = 
      extractMedia[Audio](props.get("og:audio")){
        case (url, i) => 
          Audio(
            url,
            `type` = props.get("og:audio:type").flatMap(_.lift(i)),
            secureUrl = props.get("og:audio:secure_url").flatMap(_.lift(i))
          )
      }

}
