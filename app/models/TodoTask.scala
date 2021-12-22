package models

import io.circe.generic.extras.ConfiguredJsonCodec
import utils.CirceConfiguration

@ConfiguredJsonCodec
case class TodoTask(id: String, title: String)

object TodoTask extends CirceConfiguration