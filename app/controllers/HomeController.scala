package controllers

import javax.inject._

import play.api.mvc._
import services.Zazu

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(zazu: Zazu) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    println(zazu serviceWithId "1")
    println(zazu allServices)
    Ok(views.html.index("Your new application is ready."))
  }

  def zazuServices = Action {
    Ok(views.html.zazuservices(zazu allServices))
  }

  def updateZazuService(id: String) = Action { implicit request =>
    val newState = request.getQueryString("new-state").get
    val updatedService = zazu.updateService(id, newState)
    Ok(views.html.zazuservice(updatedService))
  }

}
