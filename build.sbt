import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.typedlabs",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "opengraph4s",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      jsoup,
      cats
    )
  )

bintrayOrganization := Some("typedlabs")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayPackageLabels := Seq("scala", "opengraph")

