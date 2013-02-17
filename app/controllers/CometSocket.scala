package controllers

import play.api.mvc.{Controller, Action}
import play.api.libs.iteratee.Enumerator
import play.api.libs.Comet

object CometSocket extends Controller {

  def comet = Action {
    val events = Enumerator("kiki", "foo", "bar")
    Ok.stream(events &> Comet(callback = "console.log"))
  }
}
