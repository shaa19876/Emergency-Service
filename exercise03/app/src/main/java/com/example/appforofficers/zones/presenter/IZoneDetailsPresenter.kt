package com.example.appforofficers.zones.presenter

interface IZoneDetailsPresenter {
    fun onZoneDetailsScreen(id: Int)
    fun onZoneIncidentsRecycle(id: Int)
    fun onIncidentDetails(id: Int)
    fun onUpdateIncidentStatus(id: Int, status: Int)
}