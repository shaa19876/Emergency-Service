package com.example.appforofficers.zones.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appforofficers.app.App
import com.example.appforofficers.databinding.FragmentZoneDetailsBinding
import com.example.appforofficers.zones.presenter.ZoneDetailsPresenter
import com.example.appforofficers.zones.view.adapters.ZoneDetailsAdapter

class ZoneDetailsFragment : Fragment(), IZoneDetailsView {

    private lateinit var binding: FragmentZoneDetailsBinding
    private lateinit var adapter: RecyclerView.Adapter<ZoneDetailsAdapter.ZoneDetailsHolder>
    val iZonesDetailsPresenter: ZoneDetailsPresenter by lazy {(activity?.application as App).appComponent.presenterZone}
    var zoneID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zoneID = requireArguments().getInt("Zone_id")
        iZonesDetailsPresenter.onAttach(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZoneDetailsBinding.inflate(inflater, container, false)
        initRecycle(emptyList())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (zoneID != null) {
            iZonesDetailsPresenter.onZoneDetailsScreen(zoneID!!)
            iZonesDetailsPresenter.onZoneIncidentsRecycle(zoneID!!)
        }
    }
    companion object {
        fun newInstance() = ZoneDetailsFragment
    }

    fun initRecycle(incidents: List<Triple<String,String,String>>) {
        adapter = ZoneDetailsAdapter(incidents, this)
        binding.apply {
            recyclerZoneIncidents.layoutManager = LinearLayoutManager(requireContext())
            recyclerZoneIncidents.adapter = adapter
        }
    }

    override fun showZoneName(name: String) {
        binding.textZoneName.text = name
    }

    override fun showZoneCords(cords: String ) {
        binding.textZoneCords.text = cords

    }

    override fun showZoneRadius(radius: String) {
        binding.textZoneRadius.visibility = View.VISIBLE
        binding.textZoneRadius.text = radius
    }

    override fun showZonePhone(phone: String) {
        binding.textZonePhone.text = phone
    }

    override fun showIncidents(incidents: List<Triple<String,String,String>>) {
        initRecycle(incidents)
    }

    override fun getDialogDetails(id: Int) {
       iZonesDetailsPresenter.onIncidentDetails(id)
    }

    override fun showDialogDetails(id: Int, data: String) {
        val alert = AlertDialog.Builder(requireContext())
        alert.setMessage(data)
            .setCancelable(false)
            .setPositiveButton("Change Status") { _, _ ->
                changeStatus(id)
            }
            .setNegativeButton("Close") {dialog,_ ->
                dialog.dismiss()
            }
        val dialog = alert.create()
        dialog.show()
    }

    override fun getZoneId(): Int {
        return zoneID!!
    }

    private fun changeStatus(id:Int) {
        val alert = AlertDialog.Builder(requireContext())
        val checkedItem = intArrayOf(-1)
        val statuses =arrayOf("New", "In Progress", "Closed")
        alert.setTitle("Choose status:")
            .setSingleChoiceItems(statuses, checkedItem[0]) { dialog, which ->
                checkedItem[0] = which
                iZonesDetailsPresenter.onUpdateIncidentStatus(id, which)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel"
            ) { _, _ -> }
        val dialog1 = alert.create()
        dialog1.show()
    }

}