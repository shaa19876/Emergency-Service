package com.example.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ZoneDBO(
  @PrimaryKey(autoGenerate = true) val id: Int,
  val name: String,
  val cords: String,
  val radius: Int?,
  val phone: String
)