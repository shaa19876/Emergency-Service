package com.example.appforofficers.incidents.view

interface IIncidentsView {
    fun getDialogDetails(id: Int) // RecyclerAdapter
    fun showIncidents(incidents: List<Triple<String,String,String>>)
    fun showDialogDetails(id: Int, data: String)
}