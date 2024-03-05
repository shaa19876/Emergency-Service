package com.example.api.database

import com.example.model.Incident
import com.example.model.IncidentForChangeStatus
import com.example.model.Zone

interface DaoFacade {
  suspend fun insertZone(zone: Zone)
  suspend fun getAllZones(): List<Zone>
  suspend fun getAllIncidents(): List<Incident>
  suspend fun getAllIncidentsByZone(idZone: Int): List<Incident>
  suspend fun insertIncident(incident: Incident)
  suspend fun updateIncident(incident: Incident)
  suspend fun updateIncidentStatus(incident: IncidentForChangeStatus)
}