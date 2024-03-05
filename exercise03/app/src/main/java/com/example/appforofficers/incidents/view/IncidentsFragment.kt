package com.example.appforofficers.incidents.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appforofficers.app.App
import com.example.appforofficers.databinding.FragmentIncidentsBinding
import com.example.appforofficers.incidents.presenter.IncidentsPresenter
import com.example.appforofficers.incidents.view.adapter.IncidentsAdapter

class IncidentsFragment : Fragment(), IIncidentsView {

    private lateinit var binding: FragmentIncidentsBinding
    private lateinit var adapter: RecyclerView.Adapter<IncidentsAdapter.IncidentsHolder>
    val iIncidentsPresenter: IncidentsPresenter by lazy { (activity?.application as App).appComponent.presenterIncidents }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iIncidentsPresenter.onAttach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncidentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iIncidentsPresenter.onIncidentsScreen()
    }

    private fun initRecycle(incidents: List<Triple<String, String, String>>) {
        adapter = IncidentsAdapter(incidents, this)
        binding.apply {
            recyclerIncidents.layoutManager = LinearLayoutManager(requireContext())
            recyclerIncidents.adapter = adapter
        }
    }

    override fun showIncidents(incidents: List<Triple<String, String, String>>) {
        initRecycle(incidents)
    }

    override fun showDialogDetails(id: Int, data: String) {
        val alert = AlertDialog.Builder(requireContext())
        alert.setMessage(data)
            .setCancelable(false)
            .setPositiveButton("Change Status") { _, _ ->
                changeStatus(id)
                iIncidentsPresenter.onIncidentsScreen()
            }
        val dialog = alert.create()
        dialog.show()
    }

    private fun changeStatus(id: Int) {
        val alert = AlertDialog.Builder(requireContext())
        val checkedItem = intArrayOf(-1)
        val statuses = arrayOf("New", "In Progress", "Closed")
        alert.setTitle("Choose status:")
            .setSingleChoiceItems(statuses, checkedItem[0]) { dialog, which ->
                checkedItem[0] = which
                iIncidentsPresenter.onUpdateIncidentStatus(id, which)
                dialog.dismiss()
            }
            .setNegativeButton(
                "Cancel"
            ) { _, _ -> }
        val dialog1 = alert.create()
        dialog1.show()
    }

    override fun getDialogDetails(id: Int) {
        iIncidentsPresenter.onIncidentDetails(id)
    }

    companion object {
        fun newInstance() = IncidentsFragment()
    }
}