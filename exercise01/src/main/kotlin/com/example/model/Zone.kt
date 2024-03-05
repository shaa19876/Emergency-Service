package com.example.model

import kotlinx.serialization.Serializable

@Serializable
open class Zone(
  var id: Int = 0,
  var name: String = "Base zone",
  open var shape: String = "Base shape",
  var cords: Array<Int> = emptyArray(),
  var radius: Int? = null,
  var phone: String = "8-800-123-12-34",
) {
  open fun check(x: Int, y: Int): Boolean {
    return true
  }
}

class Circle : Zone() {
  override var shape = "circle"
  override fun check(x: Int, y: Int): Boolean {
    val x1 = cords[0]
    val y1 = cords[1]
    val r = radius ?: 0
    return (x - x1) * (x - x1) + (y - y1) * (y - y1) <= r * r
  }
}

class Triangle : Zone() {
  override var shape = "triangle"
  override fun check(x: Int, y: Int): Boolean {
    val x1 = cords[0]
    val y1 = cords[1]
    val x2 = cords[2]
    val y2 = cords[3]
    val x3 = cords[4]
    val y3 = cords[5]
    val a = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y)
    val b = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y)
    val c = (x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y)
    return (a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)
  }
}

class Tetragon : Zone() {
  override var shape = "tetragon"
  override fun check(x: Int, y: Int): Boolean {
    val x1 = cords[0]
    val y1 = cords[1]
    val x2 = cords[2]
    val y2 = cords[3]
    val x3 = cords[4]
    val y3 = cords[5]
    val x4 = cords[6]
    val y4 = cords[7]
    val a = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y)
    val b = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y)
    val c = (x3 - x) * (y4 - y3) - (x4 - x3) * (y3 - y)
    val d = (x4 - x) * (y1 - y4) - (x1 - x4) * (y4 - y)
    return (a >= 0 && b >= 0 && c >= 0 && d >= 0) || (a <= 0 && b <= 0 && c <= 0 && d <= 0)
  }
}
