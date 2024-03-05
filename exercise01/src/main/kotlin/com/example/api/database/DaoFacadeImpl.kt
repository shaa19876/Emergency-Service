package com.example.api.database

import com.example.model.*
import com.example.storage.DatabaseMain.dbQuery
import com.example.storage.Incidents
import com.example.storage.Zones
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class DaoFacadeImpl : DaoFacade {

  override suspend fun insertZone(zone: Zone): Unit = dbQuery {
    Zones.insert {
      it[name] = zone.name
      it[shape] = zone.shape
      it[phone] = zone.phone
      it[radius] = zone.radius
      it[cords] = zone.cords.joinToString(",")
    }
  }

  override suspend fun getAllZones(): List<Zone> = dbQuery {
    Zones.selectAll().map(::resultRowToZone)
  }

  override suspend fun getAllIncidents(): List<Incident> = dbQuery {
    Incidents.selectAll().map(::resultRowToIncident)
  }

  override suspend fun getAllIncidentsByZone(idZone: Int): List<Incident> = dbQuery {
    Incidents.select { Incidents.zoneId eq idZone }.map(::resultRowToIncident)
  }

  override suspend fun insertIncident(incident: Incident): Unit = dbQuery {
    if (Incidents.select { Incidents.id eq incident.id }.empty()) {
      Incidents.insert {
        it[x] = incident.x
        it[y] = incident.y
        it[description] = incident.description
        it[phone] = incident.phone ?: ""
        it[type] = checkType(incident).value
        it[status] = incident.status.value
        it[zoneId] = checkZone(incident.x, incident.y)
      }
    } else updateIncident(incident)
  }

  override suspend fun updateIncident(incident: Incident) {
    Incidents.update({ Incidents.id eq incident.id }) {
      it[x] = incident.x
      it[y] = incident.y
      it[description] = incident.description
      it[phone] = incident.phone ?: ""
      it[type] = checkType(incident).value
      it[status] = incident.status.value
      it[zoneId] = checkZone(incident.x, incident.y)
    }
  }

  override suspend fun updateIncidentStatus(incident: IncidentForChangeStatus): Unit = dbQuery {
      Incidents.update({ Incidents.id eq incident.id }) {
        it[status] = incident.status.value
      }
  }

  private fun resultRowToIncident(row: ResultRow): Incident = Incident(
    id = row[Incidents.id].value,
    x = row[Incidents.x],
    y = row[Incidents.y],
    description = row[Incidents.description],
    phone = row[Incidents.phone],
    type = IncidentType.entries.find {
      it.value == row[Incidents.type]
    }!!,
    status = IncidentStatus.entries.find {
      it.value == row[Incidents.status]
    }!!,
    zoneId = row[Incidents.zoneId]
  )

  private fun resultRowToZone(row: ResultRow): Zone = when (row[Zones.shape]) {
    "circle" -> Circle().apply {
      id = row[Zones.id].value
      name = row[Zones.name]
      cords = row[Zones.cords].split(",").map { it.toInt() }.toTypedArray()
      radius = row[Zones.radius]!!
      phone = row[Zones.phone]
    }

    "triangle" -> Triangle().apply {
      id = row[Zones.id].value
      name = row[Zones.name]
      cords = row[Zones.cords].split(",").map { it.toInt() }.toTypedArray()
      phone = row[Zones.phone]
    }

    "tetragon" -> Tetragon().apply {
      id = row[Zones.id].value
      name = row[Zones.name]
      cords = row[Zones.cords].split(",").map { it.toInt() }.toTypedArray()
      phone = row[Zones.phone]
    }

    else -> Zone()
  }

  private fun checkZone(x: Int, y: Int): Int {
    val zones = runBlocking { DaoFacadeImpl().getAllZones() }
    var zoneId = checkCordsInZone(x, y, zones)
    if (zoneId == 0) {
      zoneId = checkCordsNearZone(x, y, zones)
    }
    return zoneId
  }

  private fun checkCordsInZone(x: Int, y: Int, zones: List<Zone>): Int {
    for (zone in zones) {
      when (zone) {
        is Circle -> if (zone.check(x, y)) return zone.id
        is Triangle -> if (zone.check(x, y)) return zone.id
        is Tetragon -> if (zone.check(x, y)) return zone.id
      }
    }
    return 0
  }

  private fun checkCordsNearZone(x: Int, y: Int, zones: List<Zone>): Int {
    var zoneId = 0
    var r = 1
    while (r < 100) {
      for (degree in 0..360) {
        val newX = x + r * cos(degree.toFloat() * PI / 180)
        val newY = y + r * sin(degree.toFloat() * PI / 180)
        zoneId = checkCordsInZone(newX.toInt(), newY.toInt(), zones)
        if (zoneId != 0) return zoneId
      }
      r++
    }
    return zoneId
  }

  private fun checkType(incident: Incident): IncidentType {
    IncidentType.entries.forEach {
      if (incident.description.lowercase().contains(it.name.lowercase()))
        return it
    }
    return IncidentType.UNKNOWN
  }
}