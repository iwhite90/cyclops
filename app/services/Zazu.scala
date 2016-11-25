package services

import com.google.inject.Inject
import model.ServiceDescription
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

/**
  * Created by ian on 25/11/2016.
  */
class Zazu @Inject() (ws: WSClient)(implicit executionContext: ExecutionContext) {

  def serviceWithId(id: String): ServiceDescription = {
    val x: Future[WSResponse] = ws.url("http://localhost:8000/zazuservice.json")
        .withHeaders("Accept" -> "application/json")
        .get()

    Await.result(x, 2 seconds).json.as[ServiceDescription]
  }

  def allServices: Seq[ServiceDescription] = {
    val x: Future[WSResponse] = ws.url("http://localhost:8000/zazuservices.json")
      .withHeaders("Accept" -> "application/json")
      .get()

    val json = Await.result(x, 2 seconds).json
    (json \ "services").as[Seq[ServiceDescription]]
  }

  def updateService(id: String, newState: String) = {
    val x: Future[WSResponse] = ws.url("http://localhost:8000/zazuservice.json")
      .withHeaders("Accept" -> "application/json")
      .get()

    Await.result(x, 2 seconds).json.as[ServiceDescription]
  }
}