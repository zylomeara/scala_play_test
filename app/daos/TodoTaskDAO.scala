package daos

import models.TodoTask
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID, Macros}
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.commands.WriteResult

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TodoTaskDAO @Inject()(mongoDB: ReactiveMongoApi)(implicit exec: ExecutionContext) {
  def collection: Future[BSONCollection] = mongoDB.database.map(_.collection("todos"))
  implicit def todosWriter: BSONDocumentWriter[TodoTask] = Macros.writer[TodoTask]
  implicit def todosReader: BSONDocumentReader[TodoTask] = Macros.reader[TodoTask]

  def getAllTasks: Future[List[TodoTask]] = {
    collection
      .flatMap(
        _.find(BSONDocument())
          .cursor[TodoTask]()
          .collect[List]()
      )
  }

  def createTask(task: TodoTask): Future[WriteResult] = {
    collection
      .flatMap(_.insert.one(task))
  }

  def deleteTaskById(taskID: String): Future[WriteResult] = {
    collection
      .flatMap(collection => Future.fromTry(BSONObjectID.parse(taskID))
        .map(id => collection.delete(ordered = false).one(BSONDocument("_id" -> id)))
      )
      .flatten
  }

  def modifyCompletedStatusByTaskID(taskID: String, status: Boolean): Future[Unit] = {
    collection
      .flatMap(collection => Future.fromTry(BSONObjectID.parse(taskID))
        .map(id => collection.update(ordered = false).one(
          q = BSONDocument("_id" -> id),
          u = BSONDocument("$set" -> BSONDocument("completed" -> status))
        )))
      .flatten
      .map(_ => ())
  }
}