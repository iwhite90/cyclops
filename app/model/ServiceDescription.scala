package model

import play.api.libs.json.Json

/**
  * Created by ian on 25/11/2016.
  */
case class ServiceDescription(id: String, msisdn: Long, imsi: Long, state: String)

object ServiceDescription {
  implicit val serviceDescriptionReads = Json.reads[ServiceDescription]
}