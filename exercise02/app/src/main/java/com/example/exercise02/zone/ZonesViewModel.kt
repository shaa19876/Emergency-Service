package com.example.exercise02.zone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.Repository
import com.example.repository.models.Zone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZonesViewModel @Inject constructor(private val repository: Repository): ViewModel() {
  private val _zones = MutableStateFlow<List<Zone>>(emptyList())
  val zones: StateFlow<List<Zone>> = _zones.asStateFlow()

  init {
    viewModelScope.launch {
      repository.clearZoneTable()
    }
  }

  fun getAllZones() {
    viewModelScope.launch {
      _zones.value = repository.getAllZones()
    }
  }
}