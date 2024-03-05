package com.example.exercise02

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.exercise02.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

  private lateinit var binding: MainActivityBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = MainActivityBinding.inflate(layoutInflater)

    setContentView(binding.root)
  }
}