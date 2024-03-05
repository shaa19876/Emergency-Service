package com.example.appforofficers.zones.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appforofficers.app.App
import com.example.appforofficers.databinding.FragmentZonesBinding
import com.example.appforofficers.zones.presenter.ZonesPresenter
import com.example.appforofficers.zones.view.adapters.ZonesAdapter

class ZonesFragment : Fragment(), IZoneView {

    private lateinit var binding: FragmentZonesBinding
    private lateinit var adapter: RecyclerView.Adapter<ZonesAdapter.ZonesHolder>
    val iZonesPresenter: ZonesPresenter by lazy{(activity?.application as App).appComponent.presenter}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iZonesPresenter.onAttach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZonesBinding.inflate(inflater, container, false)
        initRecycle(emptyList())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iZonesPresenter.onZonesScreen()
    }

    companion object {
        fun newInstance() = ZonesFragment()
    }

    fun initRecycle(names: List<Pair<Int, String>>) {
        adapter = ZonesAdapter(names)
        binding.apply {
            recyclerZones.layoutManager = LinearLayoutManager(requireContext())
            recyclerZones.adapter = adapter
        }
    }

    override fun showZones(names: List<Pair<Int, String>>) {
        initRecycle(names)
    }
}