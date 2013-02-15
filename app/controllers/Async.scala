package controllers

import play.api.mvc.{Action, Controller}
import concurrent.Future
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration._
import play.api.libs.concurrent.Promise

object Async extends Controller {

  def index = Action {
    val futureInt = Future { intensiveComputation }
    Async {
      futureInt.map(i => Ok("Result: " + i))
    }
  }

  def timeout = Action {
    val futureInt = Future { intensiveComputation }
    val timeoutFuture = Promise.timeout("Oops", 2.seconds)
    Async {
      Future.firstCompletedOf(Seq(futureInt, timeoutFuture)).map {
        case i: Int => Ok("Result:" + i)
        case t: String => InternalServerError(t)
      }
    }
  }

  def intensiveComputation = {
    Thread.sleep(5000)
    1
  }
}
