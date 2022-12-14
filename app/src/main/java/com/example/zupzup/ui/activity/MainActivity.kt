package com.example.zupzup.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.zupzup.R
import com.example.zupzup.databinding.ActivityMainBinding
import com.example.zupzup.ui.utils.toDateFormat
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initBinding()
        initNavController()
        setBottomNavVisibility()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavMain.setupWithNavController(navController)
    }

    private fun setBottomNavVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.frag_store_list -> {
                    showBottomNavigation()
                }
                R.id.frag_my_reservation -> {
                    showBottomNavigation()
                }
                R.id.frag_store_detail -> {
                    hideBottomNavigation()
                }
                R.id.frag_my_reservation_detail -> {
                    hideBottomNavigation()
                }
            }
        }
    }

    private fun hideBottomNavigation() {
        binding.bottomNavMain.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        binding.bottomNavMain.visibility = View.VISIBLE
    }
}