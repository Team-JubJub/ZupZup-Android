package com.example.zupzup.ui.activity

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.zupzup.R
import com.example.zupzup.databinding.ActivityMainBinding
import com.kakao.util.maps.helper.Utility.getKeyHash
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.gen.DaumMapLibraryAndroidMeta
import net.daum.mf.map.gen.KakaoMapLibraryAndroidMeta
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)  as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavMain.setupWithNavController(navController)
    }

    private fun setBottomNavVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.frag_store_list -> {
                    showBottomNavigation()
                }
                R.id.frag_store_detail -> {
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