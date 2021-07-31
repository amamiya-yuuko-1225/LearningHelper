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

class CourseFragment : Fragment() {

    private var list:MutableList<Course> = mutableListOf()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initCourseData()
        course.let {
            it.layoutManager = GridLayoutManager(activity,3)
            it.adapter = CourseAdapter(list,activity as Context)
        }
    }

    private fun setGrade() {
        grade_course.text = when( requireActivity().getSharedPreferences(
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
        grade_course.setOnClickListener {
            startActivity(Intent(activity, ChoosingGradeActivity::class.java))
        }
    }

    private fun initCourseData() {
        list.add(Course("微积分","https://search.bilibili.com/all?keyword=%E5%AE%8B%E6%B5%A9&from_source=webtop_search&spm_id_from=333"))
        list.add(Course("线性代数","https://www.bilibili.com/video/BV1aW411Q7x1?from=search&seid=5013188404907453527"))
    }

    override fun onResume() {
        setGrade()
        super.onResume()
    }
}