package com.example.repository

import com.example.database.models.IncidentDBO
import com.example.database.models.ZoneDBO
import com.example.repository.models.Incident
import com.example.repository.models.IncidentStatus
import com.example.repository.models.IncidentType
import com.example.repository.models.Zone
import com.example.serverapi.models.IncidentDTO
import com.example.serverapi.models.ZoneDTO

internal fun ZoneDTO.toZoneDBO(): ZoneDBO {
  return ZoneDBO(
    id = this.id,
    name = this.name,
    cords = this.cords.joinToString(
      separator = ",",
      transform = { it.toString() }
    ),
    radius = this.radius,
    phone = this.phone
  )
}

internal fun ZoneDBO.toZone(): Zone {
  return Zone(
    id = this.id,
    name = this.name,
    cords = this.cords
      .split(",")
      .map { it.toInt() },
    radius = this.radius,
    phone = this.phone
  )
}

internal fun IncidentDTO.toIncidentDBO(): IncidentDBO {
  return IncidentDBO(
    id = this.id,
    x = this.x,
    y = this.y,
    description = this.description,
    phone = this.phone,
    type = this.type,
    status = this.status
  )
}

internal fun IncidentDBO.toIncident(): Incident {
  return Incident(
    id = this.id,
    x = this.x,
    y = this.y,
    description = this.description,
    phone = this.phone,
    type = IncidentType
      .entries
      .find { it.name == this.type }!!,
    status = IncidentStatus
      .entries
      .find { it.name == this.status }!!
  )
}