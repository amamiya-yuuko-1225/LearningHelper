package com.experiment.learinghelper.checkActivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.experiment.learinghelper.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_check.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashSet
import kotlin.concurrent.thread


class CheckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        initCalendar()
        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initCalendar() {
        val sp = super.getSharedPreferences("date", Context.MODE_PRIVATE)
        val editor = sp.edit()
        val dateSet = sp.getStringSet("date",LinkedHashSet<String>())

        val c: Calendar = Calendar.getInstance()
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        val month: Int = c.get(Calendar.MONTH)
        val year: Int = c.get(Calendar.YEAR)
        val date = day.toString() + "/" + (month + 1) + "/" + year
        dateSet!!.add(date)

        thread {
            editor.putStringSet("date",dateSet)
            editor.apply()
        }

        val list = ArrayList<CalendarDay>()
        for (str in dateSet!!) {
            val strArray = str.split("/")
            list.add(CalendarDay.from(strArray[2].toInt(),strArray[1].toInt(),strArray[0].toInt()))
        }
        calendar.addDecorators(DayAccent(list))
        calendar.selectionMode = MaterialCalendarView.SELECTION_MODE_NONE

        days.text = "已连续签到${dateSet.size}天"

    }
}