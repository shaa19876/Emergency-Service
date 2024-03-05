package com.example.plugins

import com.example.api.network.incidentRouting
import com.example.api.network.zoneRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
  routing {
    zoneRouting()
    incidentRouting()
  }
}
