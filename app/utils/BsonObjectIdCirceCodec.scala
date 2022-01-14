package utils

import cats.syntax.all._
import io.circe.Decoder.Result
import io.circe.{Codec, DecodingFailure, HCursor, Json}
import reactivemongo.api.bson.BSONObjectID

trait BsonObjectIdCirceCodec {
  implicit val bsonObjectIDCodec: Codec[BSONObjectID] = new Codec[BSONObjectID] {
    override def apply(a: BSONObjectID): Json = Json.fromString(a.stringify)

    override def apply(c: HCursor): Result[BSONObjectID] = c.as[String]
      .flatMap(BSONObjectID.parse(_).toEither.leftMap(DecodingFailure.fromThrowable(_, Nil)))
  }
}
