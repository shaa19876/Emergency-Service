package com.example.repository.models

data class Zone(
  val id: Int,
  val name: String,
  val cords: List<Int>,
  val radius: Int?,
  val phone: String
)
