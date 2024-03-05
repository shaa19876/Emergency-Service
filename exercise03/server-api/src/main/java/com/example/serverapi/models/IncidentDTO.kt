package com.example.serverapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IncidentDTO(
    @SerialName("id") val id: Int,
    @SerialName("x") val x: Int,
    @SerialName("y") val y: Int,
    @SerialName("description") val description: String,
    @SerialName("phone") val phone: String?,
    @SerialName("type") val type: String,
    @SerialName("status") val status: String,
)

@Serializable
data class IncidentPostDTO(
    @SerialName("id") val id: Int,
    @SerialName("status") val status: String,
)