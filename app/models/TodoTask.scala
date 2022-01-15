package models

import io.circe.generic.extras.ConfiguredJsonCodec
import reactivemongo.api.bson.BSONObjectID
import reactivemongo.api.bson.Macros.Annotations.Key
import utils.{BsonObjectIdCirceCodec, CirceConfiguration}

@ConfiguredJsonCodec
case class TodoTask(@Key("_id") id: BSONObjectID, title: String, completed: Boolean)

object TodoTask extends CirceConfiguration with BsonObjectIdCirceCodec