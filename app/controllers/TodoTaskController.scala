package controllers

//import io.circe.syntax.EncoderOps
import models.TodoTask
import models.TodoTask._
import io.circe.syntax._
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class TodoTaskController @Inject()(cc: ControllerComponents)(implicit exec: ExecutionContext)
  extends AbstractController(cc) with Circe {

  def getTodoTaskList = Action.async {
    Future {
      Ok(
        List(
          TodoTask("1", "1"),
          TodoTask("2", "2"),
          TodoTask("3", "3"),
        ).asJson
      )
    }
  }
}
