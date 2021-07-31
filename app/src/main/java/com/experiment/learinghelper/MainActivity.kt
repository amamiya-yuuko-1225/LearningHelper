package com.experiment.learinghelper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.experiment.learinghelper.countdown.CountDownActivity
import com.experiment.learinghelper.daily.DailyActivity
import com.experiment.learinghelper.listActivity.DailyListAdapter
import com.experiment.learinghelper.weekly.WeeklyActivity
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
        weekly_report.setOnClickListener {
            val intent = Intent(this, WeeklyActivity::class.java)
            startActivity(intent)
        }

        clock.setOnClickListener {
            val intent = Intent(this, CountDownActivity::class.java)
            startActivity(intent)
        }

        daily_report.setOnClickListener {
            val intent = Intent(this, DailyActivity::class.java)
            startActivity(intent)
        }
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