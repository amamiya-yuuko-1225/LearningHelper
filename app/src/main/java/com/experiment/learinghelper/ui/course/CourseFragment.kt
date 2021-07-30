package com.experiment.learinghelper.ui.course

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.experiment.learinghelper.ChoosingGradeActivity
import com.experiment.learinghelper.R
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.grade

class CourseFragment : Fragment() {

    private lateinit var list:MutableList<Course>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setGrade()
        initCourseData()
        course.let {
            it.layoutManager = GridLayoutManager(activity,3)
            it.adapter = CourseAdapter(list,activity as Context)
        }
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    private fun setGrade() {
        grade.text = when( requireActivity().getSharedPreferences(
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
        grade.setOnClickListener {
            startActivity(Intent(activity, ChoosingGradeActivity::class.java))
        }
    }

    private fun initCourseData() {

    }

    override fun onResume() {
        setGrade()
        super.onResume()
    }
}