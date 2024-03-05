package com.example.appforofficers.zones.presenter

import com.example.appforofficers.zones.view.IZoneView
import com.example.repository.Repository
import com.example.repository.models.Zone
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZonesPresenter @Inject constructor(val repository: Repository) : IZonesPresenter {

    private lateinit var iZoneView: IZoneView
    fun onAttach(view: IZoneView) {
        iZoneView = view
    }

    private var zones = listOf<Zone>()
    override fun onZonesScreen() {
        CoroutineScope(Dispatchers.IO).launch {
            zones = async { repository.getAllZones() }.await()
            val names = zones.map { Pair(it.id, it.name) }
            withContext(Dispatchers.Main) {
                iZoneView.showZones(names)
            }
        }
    }
}