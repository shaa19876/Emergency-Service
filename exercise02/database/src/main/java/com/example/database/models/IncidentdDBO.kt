package com.example.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IncidentDBO(
  @PrimaryKey(autoGenerate = true) val id: Int,
  val x: Int,
  val y: Int,
  val description: String,
  val phone: String?,
  val type: String,
  val status: String,
)
