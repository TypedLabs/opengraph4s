package com.typedlabs.opengraph4s

sealed trait OpenGraphMedia

case class Image(
  url: String,
  secureUrl: Option[String] = None,
  `type`: Option[String]  = None,
  width: Option[String] = None,
  height: Option[String] = None
) extends OpenGraphMedia

case class Video(
  url: String,
  secureUrl: Option[String] = None,
  `type`: Option[String] = None,
  width: Option[String] = None,
  height: Option[String] = None,
  tags: List[String] = Nil
) extends OpenGraphMedia

case class Audio(
  url: String,
  secureUrl: Option[String],
  `type`: Option[String]
) extends OpenGraphMedia
