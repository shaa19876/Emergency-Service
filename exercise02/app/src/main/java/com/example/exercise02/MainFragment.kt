package com.example.exercise02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.exercise02.adapter.ViewPageAdapter
import com.example.exercise02.databinding.MainFragmentBinding
import com.example.exercise02.incident.IncidentsFragment
import com.example.exercise02.zone.ZonesFragment
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

  private lateinit var binding: MainFragmentBinding

  private val vm: MainFragmentViewModel by viewModels()

  private val fragmentList = listOf(
    ZonesFragment(),
    IncidentsFragment(),
  )

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = MainFragmentBinding.inflate(layoutInflater)

    binding.viewPager.adapter = ViewPageAdapter(this, fragmentList)

    binding.composeView.apply {
      setContent {
        MainTabs()
      }
    }

    return binding.root
  }

  override fun onResume() {
    super.onResume()

    binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
      override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        vm.onTabSelected(position)
      }
    })

   lifecycleScope.launch {
     vm.selectedTabIndex.collect {
       binding.viewPager.setCurrentItem(it, true)
     }
   }
  }
}

@Composable
fun MainTabs(vm: MainFragmentViewModel = viewModel()) {

  val tabTitles = listOf("Zones", "Incidents")

  val selectedTabIndex = vm.selectedTabIndex.collectAsState()

  TabRow(selectedTabIndex = selectedTabIndex.value) {
    tabTitles.forEachIndexed { index, title ->
      Tab(
        selected = selectedTabIndex.value == index,
        onClick = { vm.onTabSelected(index) },
      ) {
        Text(text = title, fontSize = 20.sp, lineHeight = 50.sp)
      }
    }
  }
}

@Composable
@Preview
fun MainFragmentScreenPreview() {
  MainTabs()
}
