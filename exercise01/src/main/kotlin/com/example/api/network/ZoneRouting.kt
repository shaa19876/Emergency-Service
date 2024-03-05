package com.example.api.network

import com.example.api.database.DaoFacadeImpl
import com.example.plugins.addSimpleDataZone
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Route.zoneRouting() {
  val dao = DaoFacadeImpl()
  CoroutineScope(Dispatchers.IO).launch { addSimpleDataZone() }

  route("/zones") {
    get {
      val zones = dao.getAllZones()
      call.respond(zones)
    }
  }
}