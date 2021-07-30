package com.experiment.learinghelper

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }
    private fun initUI() {
        actionBar!!.hide()
        setNav()
    }
     private fun setNav() {
         val navView: BottomNavigationView = findViewById(R.id.nav_view)
         val navController = findNavController(R.id.nav_host_fragment)
         val appBarConfiguration = AppBarConfiguration(setOf(
             R.id.navigation_home, R.id.navigation_course, R.id.navigation_question,R.id.navigation_me))
         setupActionBarWithNavController(navController, appBarConfiguration)
         navView.setupWithNavController(navController)
     }


}