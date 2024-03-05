package com.example.storage

import org.jetbrains.exposed.dao.id.IntIdTable

object Zones: IntIdTable() {
  val name = varchar("name", 128)
  val shape = varchar("shape", 16)
  val phone = varchar("phone", 16)
  val radius = integer("radius").nullable()
  val cords = varchar("cords", 128)
}