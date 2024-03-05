package com.example.exercise02

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainFragmentViewModel : ViewModel() {
  private val _selectedTabIndex = MutableStateFlow(0)
  val selectedTabIndex: StateFlow<Int> = _selectedTabIndex

  fun onTabSelected(index: Int) {
    _selectedTabIndex.value = index
  }
}