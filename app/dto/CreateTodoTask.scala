package dto

import io.circe.generic.extras.ConfiguredJsonCodec
import models.TodoTask
import reactivemongo.api.bson.BSONObjectID
import utils.CirceConfiguration

@ConfiguredJsonCodec
case class CreateTodoTask(title: String, completed: Boolean) {
  def toTask: TodoTask = TodoTask(
    id = BSONObjectID.generate(),
    title = title,
    completed = completed,
  )
}

object CreateTodoTask extends CirceConfiguration
