package com.example.exercise02.incident

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun IncidentsScreen(vm: IncidentsViewModel = viewModel()) {

  val incidents = vm.incidents.collectAsState()

  val showDialog = vm.showDialog.collectAsState()

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          vm.changeShowDialog()
          vm.editMode()
        },
        modifier = Modifier.width(200.dp)
      )
      {
        Text(text = "Declare incident", fontSize = 20.sp)
      }
    },
    floatingActionButtonPosition = FabPosition.Center
  ) {
    if (showDialog.value) {
      Dialog()
    }
    LazyColumn(
      Modifier
        .background(Color.LightGray)
        .fillMaxSize()
        .padding(it)
    ) {
      itemsIndexed(incidents.value) { index, incident ->
        IncidentCardItem(
          id = index,
          type = incident.type.value,
          status = incident.status.value
        )
      }
    }
  }

}

@Composable
fun IncidentCardItem(id: Int, type: String, status: String, vm: IncidentsViewModel = viewModel()) {
  OutlinedCard(
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp)
      .padding(5.dp),
    onClick = {
      vm.changeShowDialog()
      vm.viewMode(id)
    }
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxSize()
    ) {
      Column {
        Text(
          text = "Type: $type",
          fontSize = 20.sp,
          modifier = Modifier
            .padding(5.dp)
        )
        Text(
          text = "Status: $status",
          fontSize = 20.sp,
          modifier = Modifier
            .padding(5.dp)
        )
      }
    }
  }
}

@Composable
fun Dialog(vm: IncidentsViewModel = viewModel()) {

  val incidents = vm.incidents.collectAsState()
  val id = vm.idIncident.collectAsState()
  val editMode = vm.editMode.collectAsState()

  val title = if (editMode.value) "Edit Mode" else "View Mode"
  var description by remember { mutableStateOf("") }
  var phone by remember { mutableStateOf("") }
  var x by remember { mutableStateOf("") }
  var y by remember { mutableStateOf("") }

  AlertDialog(
    onDismissRequest = {
      vm.changeShowDialog()
    },
    title = { Text(title) },
    text = {
      if (editMode.value) {
        Column(verticalArrangement = Arrangement.SpaceAround) {
          OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            isError = description.isEmpty()
          )
          OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = phone.isEmpty()
          )
          Row(
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            OutlinedTextField(
              value = x,
              onValueChange = { x = it },
              label = { Text("X") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              modifier = Modifier.weight(1f),
              isError = x.isEmpty()
            )
            OutlinedTextField(
              value = y,
              onValueChange = { y = it },
              label = { Text("Y") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              modifier = Modifier.weight(1f),
              isError = y.isEmpty()
            )
          }
        }
      } else {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
          Text(text = "Type: ${incidents.value.elementAt(id.value).type}")
          Text(text = "Description: ${incidents.value.elementAt(id.value).description}")
          incidents.value.elementAt(id.value).phone?.let { Text(text = "Phone: $it") }
          Text(text = "Status: ${incidents.value.elementAt(id.value).status.value}")
          Text(
            text =
            "Cords: [${incidents.value.elementAt(id.value).x}, " +
                    "${incidents.value.elementAt(id.value).y}]"
          )
        }
      }
    },
    confirmButton = {
      if (editMode.value) {
        Button(
          onClick = {
            if (description.isNotEmpty() && phone.isNotEmpty() && x.isNotEmpty() && y.isNotEmpty()) {
              vm.changeShowDialog()
              vm.postIncident(x.toInt(), y.toInt(), description, phone)
            }
          }
        ) {
          Text("Declare")
        }
      } else {
        Button(
          onClick = {
            vm.changeShowDialog()
          }
        ) {
          Text("Close")
        }
      }
    },
    dismissButton = {
      if (editMode.value) {
        Button(
          onClick = {
            vm.changeShowDialog()
          }
        ) {
          Text("Cancel")
        }
      }
    }
  )

}

@Preview
@Composable
fun IncidentCardItemPreview() {
  IncidentCardItem(0, "Type of incident", "Status of incident")
}