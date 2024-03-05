package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class IncidentForChangeStatus(
  val id: Int,
  val status: IncidentStatus,
)
