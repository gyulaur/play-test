package controllers

import play.api.mvc.{WebSocket, Controller}
import play.api.libs.iteratee.{Enumerator, Iteratee}

object WebSockets extends Controller {

  def index = WebSocket.using[String] { request =>

    val in = Iteratee.foreach[String](println).mapDone { _ =>
      println("Disconnected.")
    }

    val out = Enumerator("Hello!")

    (in, out)
  }
}
