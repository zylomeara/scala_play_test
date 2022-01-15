package services

import daos.TodoTaskDAO
import models.TodoTask
import reactivemongo.api.commands.WriteResult

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TodoTaskService @Inject()(taskDAO: TodoTaskDAO)(implicit exec: ExecutionContext) {
  def getAllTasks: Future[List[TodoTask]] = taskDAO.getAllTasks

  def createTask(todoTask: TodoTask): Future[WriteResult] = taskDAO.createTask(todoTask)

  def deleteTaskById(taskID: String): Future[WriteResult] = taskDAO.deleteTaskById(taskID)

  def modifyCompletedStatusByTaskID(taskID: String, status: Boolean): Future[Unit] = taskDAO.modifyCompletedStatusByTaskID(taskID, status)
}
