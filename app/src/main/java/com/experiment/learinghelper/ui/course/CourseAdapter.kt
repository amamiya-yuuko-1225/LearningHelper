package com.experiment.learinghelper.ui.course

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.experiment.learinghelper.R
import com.experiment.learinghelper.listActivity.ListAdapter

class CourseAdapter(private val list:List<Course>,val context: Context):RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val course:TextView = view.findViewById(R.id.course)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.course.text = item.course
        holder.course.setOnClickListener {
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.data = Uri.parse(item.url)
            context.startActivity(intent)
        }
    }


}