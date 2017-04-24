package com.typedlabs.opengraph4s

import org.scalatest._

class OpenGraphSpec extends FlatSpec with Matchers {

  val lightBendPost = OpenGraph.extract("http://www.lightbend.com/blog/lightbend-tech-digest-march-2017")

  "LightBend blog post" should "have title" in {
    lightBendPost.title should be (Some("Lightbend Tech Digest - March 2017 - @lightbend"))
  }

  it should "have at least 1 image" in {
    lightBendPost.images.length should be (1)
  }

  it should "have a description" in {
    lightBendPost.description shouldBe defined
  }  

  it should "have a url" in {
    lightBendPost.url shouldBe Some("https://www.lightbend.com/blog/lightbend-tech-digest-march-2017")
  }    

  it should "have a siteName" in {
    lightBendPost.siteName shouldBe Some("Lightbend")
  }      

  it should "have the og:type" in {
    lightBendPost.`type` shouldBe Some("article")
  }        

  it should "have empty determiner" in {
    lightBendPost.determiner shouldBe empty
  }  

  val pin = OpenGraph.extract("https://cz.pinterest.com/pin/AbSG5TJ_j3A0TUh5kQTR2AAMG-tbZ9kfPI037UgVZ4mug3hAazciGjA/")

  "Pinterest pin" should "have title" in {
    pin.title shouldBe defined
  }

  it should "have at least 1 image" in {
    pin.images.length should be (1)
  }

  it should "have at least 1 image with width and height" in {
    pin.images.headOption.map{ image =>
      image.width shouldBe defined
    }   

    pin.images.headOption.map{ image =>
      image.height shouldBe defined
    }   

  }

  it should "have a description" in {
    pin.description shouldBe defined
  }  

  it should "have a url" in {
    pin.url shouldBe Some("https://www.pinterest.com/pin/356347389252462971/")
  }    

  it should "have a siteName" in {
    pin.siteName shouldBe Some("Pinterest")
  }      

  it should "have the og:type" in {
    pin.`type` shouldBe Some("pinterestapp:pin")
  }        

  it should "have empty determiner" in {
    pin.determiner shouldBe empty
  }  


  val youtube = OpenGraph.extract("https://www.youtube.com/watch?v=AAMncMqpOds")

  "Youtube video" should "have title" in {
    youtube.title shouldBe defined
  }

  it should "have at least 1 image" in {
    youtube.images.length should be (1)
  }

  it should "have a video with width" in {
    youtube.videos.headOption.map{ vid =>
      vid.width shouldBe defined
    }   
  }

  it should "have a video with height" in {
    youtube.videos.headOption.map{ vid =>
      vid.height shouldBe defined
    }   
  }  

  it should "have at least a video with type" in {
    youtube.videos.headOption.map{ vid =>
      vid.`type` shouldBe defined
    }
  }  

  it should "have at least a video with secureUrl" in {
    youtube.videos.headOption.map{ vid =>
      vid.secureUrl shouldBe defined
    }
  }    

  it should "have a description" in {
    youtube.description shouldBe defined
  }  

  it should "have a url" in {
    youtube.url shouldBe Some("https://www.youtube.com/watch?v=AAMncMqpOds")
  }    

  it should "have a siteName" in {
    youtube.siteName shouldBe Some("YouTube")
  }      

  it should "have the og:type" in {
    youtube.`type` shouldBe Some("video")
  }        

  it should "have empty determiner" in {
    youtube.determiner shouldBe empty
  }  


}
