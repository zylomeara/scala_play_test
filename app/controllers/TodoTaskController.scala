package controllers

import dto.CreateTodoTask
import models.TodoTask._
import io.circe.syntax._
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.TodoTaskService

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class TodoTaskController @Inject()(
  cc: ControllerComponents,
  taskService: TodoTaskService,
)(implicit exec: ExecutionContext)
  extends AbstractController(cc) with Circe {
  def getTodoTaskList: Action[AnyContent] = Action.async {
    taskService.getAllTasks
      .map(_.asJson)
      .map(Ok(_))
  }

  def completeTask(taskID: String): Action[AnyContent] = Action.async {
    taskService.modifyCompletedStatusByTaskID(taskID, true)
      .map(_ => NoContent)
  }

  def uncompleteTask(taskID: String): Action[AnyContent] = Action.async {
    taskService.modifyCompletedStatusByTaskID(taskID, false)
      .map(_ => NoContent)
  }

  def createTask: Action[CreateTodoTask] = Action.async(circe.json[CreateTodoTask]) { request =>
    taskService.createTask(request.body.toTask)
      .map(_ => NoContent)
  }

  def deleteTask(taskID: String): Action[AnyContent] = Action.async {
    taskService.deleteTaskById(taskID)
      .map(_ => NoContent)
  }
}
