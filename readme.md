# opengraph4s

Parses given html data into Facebook OpenGraph structure.

1. [Set Up](#Set Up)
2. [Usage](#usage)

## Quick start

### Set Up

[ ![Download](https://api.bintray.com/packages/typedlabs/releases/opengraph4s/images/download.svg) ](https://bintray.com/typedlabs/releases/opengraph4s/_latestVersion)

In `plugins.sbt`, add the following bintray resolver

```scala
Resolver.bintrayIvyRepo("typedlabs", "releases")
```

In `build.sbt`, set the opengraph4s version in a variable (for the latest version, set `val opengraph4sVersion =` the version you see
in the bintray badge above).

```scala
libraryDependencies ++= Seq(
    "com.typedlabs" %% "opengraph4s" % opengraph4sVersion
)
```

### Usage

Using opengraph4s is simple. It only needs an implicit ExecutionContext to run it's Future

```scala

import com.typedlabs.opengraph4s.OpenGraph
import com.typedlabs.opengraph4s.OpenGraphNotFound

val opengraphData: Future[Either[OpenGraphNotFound, OpenGraph]] = 
  OpenGraph.extract("http://www.lightbend.com/blog/lightbend-tech-digest-march-2017")

```

`OpenGraph` case class is composed off optional fields as not all web pages have a consistent representation of open graph meta tags.

