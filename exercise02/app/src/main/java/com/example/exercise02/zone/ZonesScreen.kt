package com.example.exercise02.zone

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ZonesScreen(vm: ZonesViewModel = viewModel(), onNavigate: (Bundle) -> Unit) {

  val zones = vm.zones.collectAsState()

  LazyColumn(
    Modifier.background(Color.LightGray)
  ) {
    items(zones.value) { zone ->
      val bundle = Bundle()
      bundle.putInt("idZone", zone.id)
      ZoneCardItem(text = zone.name) { onNavigate(bundle) }
    }
  }
}

@Composable
fun ZoneCardItem(text: String, onNavigate: () -> Unit) {
  OutlinedCard(
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp)
      .padding(5.dp),
    onClick = { onNavigate() }
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxSize()
    ) {
      Text(
        text = text,
        fontSize = 20.sp,
        modifier = Modifier
          .padding(start = 10.dp)
      )
    }
  }
}

@Preview
@Composable
fun ZoneCardItemPreview() {
  ZoneCardItem("Name of zone") {}
}