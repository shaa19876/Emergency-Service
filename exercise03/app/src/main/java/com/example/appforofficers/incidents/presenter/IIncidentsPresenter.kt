package com.example.appforofficers.incidents.presenter

interface IIncidentsPresenter {
    fun onIncidentsScreen()
    fun onUpdateIncidentStatus(id: Int, status: Int)
    fun onIncidentDetails(id: Int)
}