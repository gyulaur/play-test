package controllers

import play.api.mvc.{Action, Controller}

object SessionTest extends Controller {

  def index = Action { implicit request =>
    session.get("connected").map {
      user => Ok("Hello " + user)
    } getOrElse {
      Unauthorized("Ooops, you're not connected")
    }
  }

  def putValue = Action { implicit request =>
    Ok("Welcome!").withSession(session + ("connected" -> "user@gmail.com") + ("test" -> "gyula"))
  }

  def reset = Action { implicit request =>
    SeeOther("/ses").withSession(session - "connected")
  }

  def discard = Action { implicit request =>
    SeeOther("/ses").withNewSession
  }

  def fl = Action { implicit request =>
    Ok(flash.get("success").getOrElse("Welcome!"))
  }

  def fls = Action { implicit request =>
    Redirect("/ses/fl").flashing("success" -> "The item has been created")
  }
}
