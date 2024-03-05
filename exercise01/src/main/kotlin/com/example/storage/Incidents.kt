package com.example.storage

import org.jetbrains.exposed.dao.id.IntIdTable

object Incidents: IntIdTable() {
  val x = integer("x")
  val y = integer("y")
  val description = varchar("description", 1024)
  val phone = varchar("phone", 16).nullable()
  val type = varchar("incidentType", 16).nullable()
  val status = varchar("status", 16)
  val zoneId = integer("zoneId").nullable()
}