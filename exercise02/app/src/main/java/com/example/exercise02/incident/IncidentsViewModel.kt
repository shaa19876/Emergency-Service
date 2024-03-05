package com.example.exercise02.incident

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.Repository
import com.example.repository.models.Incident
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncidentsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
  private val _incidents = MutableStateFlow<List<Incident>>(emptyList())
  val incidents: StateFlow<List<Incident>> = _incidents.asStateFlow()

  private val _showDialog = MutableStateFlow<Boolean>(false)
  val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

  private val _editMode = MutableStateFlow<Boolean>(false)
  val editMode: StateFlow<Boolean> = _editMode.asStateFlow()

  private val _idIncident = MutableStateFlow<Int>(0)
  val idIncident: StateFlow<Int> = _idIncident.asStateFlow()

  init {
    viewModelScope.launch {
      repository.clearIncidentTable()
    }
  }

  fun getAllIncidents() {
    viewModelScope.launch {
      _incidents.value = repository.getAllIncidents()
    }
  }

  fun postIncident(x: Int, y: Int, description: String, phone: String) {
    viewModelScope.launch {
      repository.postIncident(x, y, description, phone)
      _incidents.value = repository.getAllIncidents()
    }
  }

  fun changeShowDialog() {
    _showDialog.value = !showDialog.value
  }

  fun editMode() {
    _editMode.value = true
  }

  fun viewMode(idIncident: Int) {
    _editMode.value = false
    _idIncident.value = idIncident
  }
}