package com.example.exercise02.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(fa:Fragment, private val listFragment: List<Fragment>):FragmentStateAdapter(fa) {
  override fun getItemCount(): Int {
    return listFragment.size
  }

  override fun createFragment(position: Int): Fragment {
    return listFragment.elementAt(position)
  }

}
