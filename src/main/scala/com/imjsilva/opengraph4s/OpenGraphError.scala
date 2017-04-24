package com.typedlabs.opengraph4s

sealed trait OpenGraphError
case class OpenGraphNotFound() extends OpenGraphError