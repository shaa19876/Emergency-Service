package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Incident(
  val id: Int = 0,
  val x: Int,
  val y: Int,
  val description: String,
  val phone: String?,
  val type: IncidentType = IncidentType.UNKNOWN,
  val status: IncidentStatus = IncidentStatus.NEW,
  val zoneId: Int? = null,
)

@Serializable
enum class IncidentType(val value: String) {
  FIRE("fire"),
  GAS("gas leak"),
  CAT("cat on the tree"),
  UNKNOWN("unknown type")
}

@Serializable
enum class IncidentStatus(val value: String){
  NEW("New"),
  PROGRESS("In progress"),
  CLOSE("Close"),
}