package com.example.appforofficers.incidents.presenter

import com.example.appforofficers.incidents.view.IIncidentsView
import com.example.repository.Repository
import com.example.repository.models.Incident
import com.example.repository.models.IncidentStatus
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IncidentsPresenter @Inject constructor(val repository: Repository): IIncidentsPresenter {
    private lateinit var iIncidentsView: IIncidentsView
    fun onAttach(view: IIncidentsView) {
        iIncidentsView = view
    }

    private var incidents = listOf<Incident>()

    override fun onIncidentsScreen() {
        CoroutineScope(Dispatchers.IO).launch {
            incidents = async { repository.getAllIncidents() }.await()
            val data = incidents.map { Triple(it.id.toString(), it.type.name, it.status.name) }
            withContext(Dispatchers.Main) {
                iIncidentsView.showIncidents(data)
            }
        }
    }

    override fun onIncidentDetails(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val incident = async { repository.getIncident(id) }.await()
            val data = """
                Cords of incident: ${incident.x} , ${incident.y}
                Description: ${incident.description}
                Phone: ${incident.phone?.let { it }}
                Type: ${incident.type}
                Status: ${incident.status}
                """
            withContext(Dispatchers.Main) {
                iIncidentsView.showDialogDetails(incident.id, data)
            }
        }
    }

    override fun onUpdateIncidentStatus(id: Int, status: Int) {
        val newStatus = when(status) {
            1 -> IncidentStatus.PROGRESS
            2 -> IncidentStatus.CLOSE
            else -> IncidentStatus.NEW
        }
        CoroutineScope(Dispatchers.IO).launch {
            val incident = async { repository.getIncident(id)}.await()
            with(incident) {repository.postIncident(id, newStatus.name ) }
            onIncidentsScreen()
        }
    }
}