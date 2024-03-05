package com.example.repository

import com.example.database.MainDatabase
import com.example.repository.models.Incident
import com.example.repository.models.Zone
import com.example.serverapi.ServerApi
import com.example.serverapi.models.IncidentPostDTO
import jakarta.inject.Inject

class Repository @Inject constructor(
  private val db: MainDatabase,
  private val api: ServerApi,
) {
  suspend fun getAllZones(): List<Zone> {
    val zonesFromServer = api.getZones()

    db.dao().insertAllZones(zonesFromServer.map { it.toZoneDBO() })


    return db.dao()
      .getAllZones()
      .map { it.toZone() }
  }

  suspend fun getZone(idZone: Int): Zone {
    return db.dao()
      .getZone(idZone)
      .toZone()
  }

  suspend fun getAllIncidents(): List<Incident> {
    val incidentsFromServer = api.getIncidents()

    db.dao().insertAllIncidents(incidentsFromServer.map { it.toIncidentDBO() })

    return db.dao()
      .getAllIncidents()
      .map { it.toIncident() }
  }

  suspend fun getIncident(idIncident: Int): Incident {
    return db.dao()
      .getIncident(idIncident)
      .toIncident()
  }

  suspend fun postIncident(x: Int, y: Int, description: String, phone: String) {
    api.postIncident(
      IncidentPostDTO(
      x = x,
      y = y,
      description = description,
      phone = phone
    )
    )
  }

  suspend fun clearIncidentTable() {
    db.dao().clearIncidentTable()
  }

  suspend fun clearZoneTable() {
    db.dao().clearZoneTable()
  }
}