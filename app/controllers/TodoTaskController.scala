package controllers

//import io.circe.syntax.EncoderOps
import daos.ReactiveMongoApi
import dto.CreateTodoTask
import models.TodoTask
import models.TodoTask._
import io.circe.syntax._
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, Macros}
import reactivemongo.api.bson.collection.BSONCollection

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class TodoTaskController @Inject()(cc: ControllerComponents, mongoDB: ReactiveMongoApi)(implicit exec: ExecutionContext)
  extends AbstractController(cc) with Circe {
  def todosCollection: Future[BSONCollection] = mongoDB.database.map(_.collection("todos"))
  implicit def personWriter: BSONDocumentWriter[TodoTask] = Macros.writer[TodoTask]

  def getTodoTaskList = Action.async {
    implicit def personReader: BSONDocumentReader[TodoTask] = Macros.reader[TodoTask]

    todosCollection
      .flatMap(
        _.find(BSONDocument())
          .cursor[TodoTask]()
          .collect[List]()
      )
      .map(_.asJson)
      .map(Ok(_))
  }

  def completeTask(taskID: Int) = Action.async {
    Future {
      Ok(taskID.asJson)
    }
  }

  def uncompleteTask(taskID: Int) = Action.async {
    Future {
      Ok(taskID.asJson)
    }
  }

  def createTask = Action.async(circe.json[CreateTodoTask]) { request =>
    todosCollection
      .flatMap(_.insert.one(request.body.toTask))
      .map(_ => Ok(""))
  }
}
