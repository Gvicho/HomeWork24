package com.example.homework24.presentation

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.homework24.R
import com.example.homework24.databinding.ActivityMainBinding
import com.example.homework24.presentation.extensions.safeNavigateWithArguments
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val requestPermission: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { it: Map<String, Boolean> ->
            // Handle permissions result
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("tag123","activity onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        requestPermission()
        binding.bottomNavigation.selectedItemId = R.id.navigation_home


    }

    override fun onStart() {
        super.onStart()
        Log.d("tag123","activity onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("tag123","activity onResume")
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        Log.d("tag123","handling intent")
        intent.extras?.let { bundle ->
            if (bundle.containsKey("postId")) {
                val postId = bundle.getInt("postId", -1)
                Log.d("tag123","bundle contained key, it was  $postId")
                navigateToDetailsFragment(postId)
            }
        }
    }


    private fun navigateToDetailsFragment(postId: Int) {
        Log.d("tag123", "Trying to navigate to details page with post ID $postId")
        val bundle = bundleOf("id" to postId) // Ensure key matches argument name in nav graph
        val navController = findNavController(R.id.navigationHostFrame)
        // Use your extension function for safe navigation with arguments
        navController.safeNavigateWithArguments(R.id.action_homePageFragment_to_detailsFragment, bundle)
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
        }
    }
}