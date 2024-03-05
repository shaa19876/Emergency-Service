package com.example.appforofficers.zones.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appforofficers.R
import com.example.appforofficers.databinding.IncidentItemBinding
import com.example.appforofficers.zones.view.IZoneDetailsView

class ZoneDetailsAdapter(
    val incidents: List<Triple<String, String, String>>,
    val view: IZoneDetailsView
) : RecyclerView.Adapter<ZoneDetailsAdapter.ZoneDetailsHolder>(), IZoneDetailsAdapter {
    class ZoneDetailsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = IncidentItemBinding.bind(item)
        fun bind(incident: Triple<String, String, String>) = with(binding) {
            textIncidentType.text = incident.second
            textIncidentStatus.text = incident.third
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneDetailsHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.incident_item, parent, false)
        return ZoneDetailsHolder(view)
    }

    override fun getItemCount(): Int {
        return incidents.size
    }

    override fun onBindViewHolder(holder: ZoneDetailsHolder, position: Int) {
        holder.bind(incidents[position])
        holder.binding.cardIncident.setOnClickListener {
            showDialogDetails(incidents[position].first.toInt())
        }
    }

    override fun showDialogDetails(id: Int) {
        view.getDialogDetails(id)
    }
}