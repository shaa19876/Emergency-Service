package com.example.exercise02.zone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZoneDetailFragment: Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    val idZone = requireArguments().getInt("idZone")
    return ComposeView(requireContext()).apply {
      setContent {
          ZoneDetailScreen(idZone = idZone)
      }
    }
  }
}