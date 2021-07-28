package com.experiment.learinghelper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choosing_grade.*

class ChoosingGradeActivity : AppCompatActivity() {
    private val editor = getSharedPreferences("grade", Context.MODE_PRIVATE).edit()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosing_grade)
        freshman.setOnClickListener {
            editor.putInt("grade",1)
        }
        sophomore.setOnClickListener {
            editor.putInt("grade",2)
        }
        junior.setOnClickListener {
            editor.putInt("grade",3)
        }
        senior.setOnClickListener {
            editor.putInt("grade",4)
        }
        graduate_1.setOnClickListener {
            editor.putInt("grade",5)
        }
        graduate_2.setOnClickListener {
            editor.putInt("grade",6)
        }
        graduate_3.setOnClickListener {
            editor.putInt("grade",7)
        }
        save.setOnClickListener {
            onBackPressed()
        }
        back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        editor.apply()
        super.onBackPressed()
    }
}