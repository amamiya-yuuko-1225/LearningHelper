package com.experiment.learinghelper.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.experiment.learinghelper.ChoosingGradeActivity
import com.experiment.learinghelper.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setGrade()
        return inflater.inflate(R.layout.fragment_home, container, false)
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
            startActivity(Intent(activity,ChoosingGradeActivity::class.java))
        }
    }

    override fun onResume() {
        setGrade()
        super.onResume()
    }
}