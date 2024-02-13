package com.example.homework24.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework24.R
import com.example.homework24.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigation.selectedItemId = R.id.navigation_home
    }
}