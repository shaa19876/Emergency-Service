package com.example.exercise02.zone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.exercise02.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZonesFragment : Fragment() {

  private val vm: ZonesViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    vm.getAllZones()

    return ComposeView(requireContext()).apply {
      setContent {
        ZonesScreen(
          onNavigate = { bundle ->
            findNavController().navigate(
              R.id.action_mainFragment_to_zoneDetailFragment,
              bundle
            )
          }
        )
      }
    }
  }
}