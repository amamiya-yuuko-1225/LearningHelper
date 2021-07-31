package com.experiment.learinghelper.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.experiment.learinghelper.ChoosingGradeActivity
import com.experiment.learinghelper.R
import com.experiment.learinghelper.checkActivity.CheckActivity
import com.experiment.learinghelper.countdown.CountDownActivity
import com.experiment.learinghelper.daily.DailyActivity
import com.experiment.learinghelper.listActivity.ListActivity
import com.experiment.learinghelper.weekly.WeeklyActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.check

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setGrade()
        initUI()
    }
    private fun initUI() {
        val weeklyReport = requireView().findViewById<Button>(R.id.weekly_report)
        val clock = requireView().findViewById<Button>(R.id.clock)
        val dailyReport = requireView().findViewById<Button>(R.id.daily_report)
        weeklyReport.setOnClickListener {
            val intent = Intent(activity, WeeklyActivity::class.java)
            startActivity(intent)
        }

        clock.setOnClickListener {
            val intent = Intent(activity, CountDownActivity::class.java)
            startActivity(intent)
        }

        dailyReport.setOnClickListener {
            val intent = Intent(activity, DailyActivity::class.java)
            startActivity(intent)
        }

        check.setOnClickListener {
            startActivity(Intent(activity,CheckActivity::class.java))
        }

        list.setOnClickListener {
            startActivity(Intent(activity,ListActivity::class.java))
        }

    }
    private fun setGrade() {
        val gradeHome = requireView().findViewById<TextView>(R.id.grade_home)
        gradeHome.text = when( requireActivity().getSharedPreferences(
            "grade", Context.MODE_PRIVATE).getInt("grade",1)) {
            1 -> "大一"
            2 -> "大二"
            3 -> "大三"
            4 -> "大四"
            5 -> "研一"
            6 -> "研二"
            7 -> "研三"
            else -> "大一"
        }
        gradeHome.setOnClickListener {
            startActivity(Intent(activity,ChoosingGradeActivity::class.java))
        }
    }

    override fun onResume() {
        setGrade()
        super.onResume()
    }
}