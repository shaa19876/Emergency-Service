package com.example.exercise02.zone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.repository.models.Zone

@Composable
fun ZoneDetailScreen(vm: ZoneDetailViewModel = viewModel(), idZone: Int) {

  vm.getZone(idZone)

  val zone = vm.zone.collectAsState()

  zone.value?.let { ZoneCard(it) }
}

@Composable
fun ZoneCard(zone: Zone) {
  Column(Modifier.background(Color.LightGray)) {
    OutlinedCard(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(10.dp)
    ) {
      Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "Zone name: " + zone.name,
            fontSize = 20.sp,
          )
        }

        Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "Cords: [" + zone.cords.joinToString(separator = ", ",
              transform = { it.toString() }) + "]",
            fontSize = 20.sp,
          )
        }

        if (zone.radius != null) {
          Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "Radius: " + zone.radius.toString(),
              fontSize = 20.sp,
            )
          }
        }

        Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "Phone: " + zone.phone,
            fontSize = 20.sp,
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun ZoneCardPreview() {
  ZoneCard(
    Zone(
      id = 0,
      name = "Test zone",
      cords = listOf(1, 2, 3, 4),
      radius = null,
      phone = "8-999-123-45-67"
    )
  )
}