package com.example.appforofficers.incidents.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appforofficers.R
import com.example.appforofficers.databinding.IncidentItemBinding
import com.example.appforofficers.incidents.view.IIncidentsView

class IncidentsAdapter(
    val incidents: List<Triple<String, String, String>>,
    val view: IIncidentsView
) : RecyclerView.Adapter<IncidentsAdapter.IncidentsHolder>(), IIncidentsAdapter{
    class IncidentsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = IncidentItemBinding.bind(item)
        fun bind(incident: Triple<String, String, String>) = with(binding) {
            textIncidentType.text = incident.second
            textIncidentStatus.text = incident.third
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentsHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.incident_item, parent, false)
        return IncidentsHolder(view)
    }

    override fun getItemCount(): Int {
        return incidents.size
    }

    override fun onBindViewHolder(holder: IncidentsHolder, position: Int) {
        holder.bind(incidents[position])
        holder.binding.cardIncident.setOnClickListener {
            showDialogDetails(incidents[position].first.toInt())
        }
    }

    override fun showDialogDetails(id: Int) {
        view.getDialogDetails(id)
    }
}