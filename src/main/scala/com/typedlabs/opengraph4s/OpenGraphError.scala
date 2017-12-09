package com.typedlabs.opengraph4s

sealed trait OpenGraphError
case object OpenGraphNotFound extends OpenGraphError