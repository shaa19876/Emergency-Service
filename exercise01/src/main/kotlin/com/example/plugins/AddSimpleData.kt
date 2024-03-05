package com.example.plugins

import com.example.api.database.DaoFacadeImpl
import com.example.model.Incident
import com.example.model.Zone

suspend fun addSimpleDataZone() {
  val dao = DaoFacadeImpl()

  if (dao.getAllZones().isEmpty()) {
    dao.insertZone(
      Zone(
        name = "Zone circle",
        shape = "circle",
        phone = "8-111-111-11-11",
        radius = 2,
        cords = arrayOf(0, 0)
      )
    )

    dao.insertZone(
      Zone(
        name = "Zone triangle",
        shape = "triangle",
        phone = "8-222-222-22-22",
        cords = arrayOf(2, 2, 4, 2, 2, 4)
      )
    )

    dao.insertZone(
      Zone(
        name = "Zone tetragon",
        shape = "tetragon",
        phone = "8-333-333-33-33",
        cords = arrayOf(0, 5, 0, 7, 2, 7, 2, 5)
      )
    )
  }
}

suspend fun addSimpleDataIncident() {
  val dao = DaoFacadeImpl()

  if (dao.getAllIncidents().isEmpty()) {
    dao.insertIncident(
      Incident(
        x = 0,
        y = 0,
        description = "My cat can't get off the tree",
        phone = "8-111-111-11-11"
      )
    )

    dao.insertIncident(
      Incident(
        x = 1,
        y = 1,
        description = "I can't find my TV remote",
        phone = "8-222-222-22-22"
      )
    )

    dao.insertIncident(
      Incident(
        x = 2,
        y = 2,
        description = "There is an UFO kidnapping a cow",
        phone = "8-333-333-33-33"
      )
    )

    dao.insertIncident(
      Incident(
        x = 1,
        y = 6,
        description = "There is a bug in compiler, help me please!",
        phone = "8-444-444-44-44"
      )
    )

    dao.insertIncident(
      Incident(
        x = 10,
        y = 10,
        description = "two number 9's, a number 9 large, a number 6 with extra dip...",
        phone = "8-555-555-55-55"
      )
    )
  }
}