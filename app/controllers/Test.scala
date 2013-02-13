package controllers

import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import play.api.mvc.ResponseHeader
import play.api.mvc.SimpleResult


object Test extends Controller {

  def index(name: String) = Action { implicit request =>
    Ok("Hello " + name )
  }

  def main = Action { Ok("Main") }

  def simple = Action { implicit request =>
    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Hello World!")
    )
  }

  def nf = Action {
    NotFound("<h1>Page not found</h1>")
  }

  def ise = Action {
    InternalServerError("Oops")
  }

  def rdr = Action {
    Redirect(routes.Test.simple())
  }

  def rdr2 = Action {
    Redirect("/test/simple", queryString = Map.empty, status = MOVED_PERMANENTLY)
  }

  def ni = TODO

  def dyn(id: Long) = Action {
    Ok("Id: " + id)
  }

  def res = Action {
    Ok(<h1>Hello World</h1>).as(HTML).withCookies(Cookie("theme", "blue"))
  }
}
