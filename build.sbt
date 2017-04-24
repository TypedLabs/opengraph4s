import Dependencies._

name := "opengraph4s"

organization := "com.typedlabs"

scalaVersion := "2.12.1"

version      := "0.1.0"

crossScalaVersions := Seq(scalaVersion.value,"2.11.8")

bintrayRepository := "releases"

bintrayOrganization := Some("typedlabs")

bintrayPackageLabels := Seq("scala", "opengraph")

bintrayVcsUrl := Some("https://github.com/TypedLabs/opengraph4s")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

publishMavenStyle := false

libraryDependencies ++= Seq(
  scalaTest % Test,
  jsoup,
  cats
)

scalacOptions ++= Seq(
    "-encoding",
    "UTF-8",
    "-deprecation",
    "-unchecked",
    "-feature",
    "-language:postfixOps",
    "-Ywarn-unused",
    "-Ywarn-unused-import",
    "-Ywarn-dead-code",
    "-Ywarn-inaccessible",
    "-Ywarn-infer-any",
    "-Ywarn-value-discard",
    "-Xlint",    
    "-Ydelambdafy:method",
    "-target:jvm-1.8",
    "-language:existentials",
    "-language:implicitConversions"
  )