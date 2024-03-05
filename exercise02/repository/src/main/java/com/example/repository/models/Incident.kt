package com.example.repository.models

data class Incident(
  val id: Int,
  val x: Int,
  val y: Int,
  val description: String,
  val phone: String?,
  val type: IncidentType,
  val status: IncidentStatus,
)