package com.typedlabs.opengraph4s

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatest._

class OpenGraphSpec extends FlatSpec with Matchers with EitherValues {

  val lightBendPost = 
    Await.result(OpenGraph.extract("https://www.lightbend.com/blog/lightbend-tech-digest-march-2017"), 5 seconds)

  "LightBend blog post" should "have title" in {
    lightBendPost.right.value.title should be (Some("Lightbend Tech Digest - March 2017 - @lightbend"))
  }

  it should "have at least 1 image" in {
    println(lightBendPost.right.value.images)
    lightBendPost.right.value.images.length should be (1)
  }

  it should "have a description" in {
    lightBendPost.right.value.description shouldBe defined
  }  

  it should "have a url" in {
    lightBendPost.right.value.url shouldBe Some("https://www.lightbend.com/blog/lightbend-tech-digest-march-2017")
  }    

  it should "have a siteName" in {
    lightBendPost.right.value.siteName shouldBe Some("Lightbend")
  }      

  it should "have the og:type" in {
    lightBendPost.right.value.`type` shouldBe Some("article")
  }        

  it should "have empty determiner" in {
    lightBendPost.right.value.determiner shouldBe empty
  }  

  val pin = 
    Await.result(OpenGraph.extract("https://cz.pinterest.com/pin/AbSG5TJ_j3A0TUh5kQTR2AAMG-tbZ9kfPI037UgVZ4mug3hAazciGjA/"), 5 seconds)

  "Pinterest pin" should "have title" in {
    pin.right.value.title shouldBe defined
  }

  it should "have at least 1 image" in {
    pin.right.value.images.length should be (1)
  }

  it should "have at least 1 image with width and height" in {
    pin.right.value.images.headOption.map{ image =>
      image.width shouldBe defined
    }   

    pin.right.value.images.headOption.map{ image =>
      image.height shouldBe defined
    }   
  }

  it should "have a description" in {
    pin.right.value.description shouldBe defined
  }  

  it should "have a url" in {
    pin.right.value.url shouldBe Some("https://www.pinterest.com/pin/356347389252462971/")
  }    

  it should "have a siteName" in {
    pin.right.value.siteName shouldBe Some("Pinterest")
  }      

  it should "have the og:type" in {
    pin.right.value.`type` shouldBe Some("pinterestapp:pin")
  }        

  it should "have empty determiner" in {
    pin.right.value.determiner shouldBe empty
  }  

  val youtube = 
    Await.result(OpenGraph.extract("https://www.youtube.com/watch?v=AAMncMqpOds"), 10 seconds)

  println(youtube)

  "Youtube video" should "be found" in {
    youtube.right.value.title shouldBe defined
  }

  it should "have at least 1 image" in {
    youtube.right.value.images.length should be (1)
  }

  it should "have a video with width" in {
    youtube.right.value.videos.headOption.map{ vid =>
      vid.width shouldBe defined
    }   
  }

  it should "have a video with height" in {
    youtube.right.value.videos.headOption.map{ vid =>
      vid.height shouldBe defined
    }   
  }  

  it should "have at least a video with type" in {
    youtube.right.value.videos.headOption.map{ vid =>
      vid.`type` shouldBe defined
    }
  }  

  it should "have at least a video with secureUrl" in {
    youtube.right.value.videos.headOption.map{ vid =>
      vid.secureUrl shouldBe defined
    }
  }    

  it should "have a description" in {
    youtube.right.value.description shouldBe defined
  }  

  it should "have a url" in {
    youtube.right.value.url shouldBe Some("https://www.youtube.com/watch?v=AAMncMqpOds")
  }    

  it should "have a siteName" in {
    youtube.right.value.siteName shouldBe Some("YouTube")
  }      

  it should "have the og:type" in {
    youtube.right.value.`type` shouldBe Some("video")
  }        

  it should "have empty determiner" in {
    youtube.right.value.determiner shouldBe empty
  }  

}


