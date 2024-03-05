package com.example.serverapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZoneDTO(
  @SerialName("id") val id: Int,
  @SerialName("name") val name: String,
  @SerialName("cords") val cords: List<Int>,
  @SerialName("radius") val radius: Int?,
  @SerialName("phone") val phone: String
)
