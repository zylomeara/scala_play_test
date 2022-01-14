package models

import io.circe.generic.extras.ConfiguredJsonCodec
import reactivemongo.api.bson.BSONObjectID
import utils.{BsonObjectIdCirceCodec, CirceConfiguration}

@ConfiguredJsonCodec
case class TodoTask(_id: BSONObjectID, title: String, completed: Boolean)

object TodoTask extends CirceConfiguration with BsonObjectIdCirceCodec