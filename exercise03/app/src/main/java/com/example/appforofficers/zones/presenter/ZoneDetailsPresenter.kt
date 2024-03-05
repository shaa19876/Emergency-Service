package com.example.appforofficers.zones.presenter

import com.example.appforofficers.zones.view.IZoneDetailsView
import com.example.repository.Repository
import com.example.repository.models.Incident
import com.example.repository.models.IncidentStatus
import com.example.repository.models.Zone
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZoneDetailsPresenter @Inject constructor(val repository: Repository) :
    IZoneDetailsPresenter {

    private lateinit var iZoneDetailsView: IZoneDetailsView
    fun onAttach(view: IZoneDetailsView) {
        iZoneDetailsView = view
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            repository.clearIncidentTable() }
    }

    private var incidents = listOf<Incident>()
    lateinit var details: Zone

    override fun onZoneDetailsScreen(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            details = async { repository.getZone(id) }.await()
            withContext(Dispatchers.Main) {
                iZoneDetailsView.showZoneName(details.name)
                iZoneDetailsView.showZoneCords(details.cords.toString())
                details.radius?.let { iZoneDetailsView.showZoneRadius(it.toString()) }
                iZoneDetailsView.showZonePhone(details.phone)
            }
        }
    }

    override fun onZoneIncidentsRecycle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            incidents = async { repository.getIncidentsByZone(id) }.await()
            val data = incidents.map { Triple(it.id.toString(), it.type.name, it.status.name) }
            withContext(Dispatchers.Main) {
                iZoneDetailsView.showIncidents(data)
            }
        }
    }

    override fun onIncidentDetails(idIncident: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val incident = async { repository.getIncident(idIncident) }.await()
            val data = """
                Cords of incident: ${incident.x} , ${incident.y}
                Description: ${incident.description}
                Phone: ${incident.phone?.let { it }}
                Type: ${incident.type}
                Status: ${incident.status}
                """
            withContext(Dispatchers.Main) {
                iZoneDetailsView.showDialogDetails(incident.id, data)
            }
        }
    }

    override fun onUpdateIncidentStatus(idIncident: Int, status: Int) {
        val newStatus = when (status) {
            1 -> IncidentStatus.PROGRESS
            2 -> IncidentStatus.CLOSE
            else -> IncidentStatus.NEW
        }
        val zoneId = iZoneDetailsView.getZoneId()
        CoroutineScope(Dispatchers.IO).launch {
            val incident = async { repository.getIncident(idIncident) }.await()
            with(incident) { repository.postIncident(id, newStatus.name) }
            onZoneIncidentsRecycle(zoneId)
        }
    }
}