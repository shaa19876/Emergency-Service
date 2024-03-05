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
class ZoneDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {
  private val _zone = MutableStateFlow<Zone?>(null)
  val zone: StateFlow<Zone?> = _zone.asStateFlow()

  fun getZone(idZone: Int) {
    viewModelScope.launch {
      _zone.value = repository.getZone(idZone)
    }
  }
}