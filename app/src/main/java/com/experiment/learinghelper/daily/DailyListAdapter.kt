package com.experiment.learinghelper.listActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.experiment.learinghelper.R
import kotlin.concurrent.thread

class DailyListAdapter(private val list:List<ListData>):
    RecyclerView.Adapter<DailyListAdapter.ViewHolder>(){

    private val map = HashMap<Int,Boolean>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title:TextView = view.findViewById(R.id.daily_title)
        val date:TextView = view.findViewById(R.id.daily_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = list.size

    override fun onBindViewHolder (holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.date.text = "${item.month+1}月${item.day}日"
        holder.title.text = item.comment
    }

}