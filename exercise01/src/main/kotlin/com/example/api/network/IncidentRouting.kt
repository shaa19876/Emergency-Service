package com.example.api.network

import com.example.api.database.DaoFacadeImpl
import com.example.model.Incident
import com.example.model.IncidentForChangeStatus
import com.example.model.IncidentStatus
import com.example.plugins.addSimpleDataIncident
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

fun Route.incidentRouting() {
  val dao = DaoFacadeImpl()
  CoroutineScope(Dispatchers.IO).launch { addSimpleDataIncident() }

  route("/incidents") {

    get {
      val incidents = dao.getAllIncidents()
      call.respond(incidents)
    }

    get("{zoneId?}") {
      val zoneId = call.parameters["zoneId"]
      val incidents = dao.getAllIncidentsByZone(zoneId!!.toInt())
      call.respond(incidents)
    }

    post {
      val requestContent = call.receiveText()

      val incident =
        try {
          Json.decodeFromString<Incident>(requestContent)
        } catch (e: Exception) {
          null
        }

      val incidentStatus =
        try {
          Json.decodeFromString<IncidentForChangeStatus>(requestContent)
        } catch (e: Exception) {
          null
        }

      if (incident != null || incidentStatus != null) {
        call.respond(HttpStatusCode.OK)
      } else {
        call.respond(HttpStatusCode.BadRequest)
      }

      if (incident != null) dao.insertIncident(incident)
      if (incidentStatus != null) dao.updateIncidentStatus(incidentStatus)
    }
  }
}