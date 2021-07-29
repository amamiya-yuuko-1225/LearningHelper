package com.experiment.learinghelper.countdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.experiment.learinghelper.R
import kotlinx.android.synthetic.main.activity_edit_clock.*

class EditClockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_clock)
        val time = Integer.parseInt(edit_time.editableText.toString())

        edit_clock_button_true.setOnClickListener {
            val intent = Intent(this, CountDownActivity::class.java)
                .apply {
                    putExtra("time", time)
                }
            startActivity(intent)
        }

        edit_clock_button_false.setOnClickListener {
            val intent = Intent(this,CountDownActivity::class.java)
            startActivity(intent)
        }
    }
}