package com.example.appforofficers.zones.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appforofficers.R
import com.example.appforofficers.databinding.ZoneItemBinding
import com.example.appforofficers.zones.presenter.IZonesPresenter
import com.example.appforofficers.zones.presenter.ZonesPresenter
import com.example.appforofficers.zones.view.IZoneView
import com.example.repository.models.Zone

class ZonesAdapter(val zones: List<Pair<Int, String>>): RecyclerView.Adapter<ZonesAdapter.ZonesHolder>() {
    class ZonesHolder(item: View): RecyclerView.ViewHolder(item){
        var binding = ZoneItemBinding.bind(item)
        fun bind(zone: String) = with(binding) {
            zoneName.text = zone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZonesHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.zone_item,parent,false)
        return ZonesHolder(view)
    }

    override fun getItemCount(): Int {
        return zones.size
    }

    override fun onBindViewHolder(holder: ZonesHolder, position: Int) {
        holder.bind(zones[position].second)
        holder.binding.zoneName.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("Zone_id", zones[position].first)
            holder.itemView.findNavController().navigate(R.id.action_mainFragment_to_zoneDetailsFragment, bundle)
        }
    }


}