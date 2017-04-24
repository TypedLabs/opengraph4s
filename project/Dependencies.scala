import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"

  lazy val jsoup = "org.jsoup" % "jsoup" % "1.10.2"

  lazy val cats = "org.typelevel" %% "cats" % "0.9.0"

  lazy val httpClient =  "org.http4s" % "http4s-blaze-client" % "0.17.0-SNAPSHOT"
}