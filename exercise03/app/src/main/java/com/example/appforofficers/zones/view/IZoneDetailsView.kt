package com.example.appforofficers.zones.view

interface IZoneDetailsView {
    fun showZoneName(name: String)
    fun showZoneCords(cords: String)
    fun showZoneRadius(radius: String)
    fun showZonePhone(phone: String)
    fun showIncidents(incidents: List<Triple<String,String,String>>)
    fun getDialogDetails(id: Int) // RecycleAdapter
    fun showDialogDetails(id: Int, data: String)
    fun getZoneId(): Int // Getter
}