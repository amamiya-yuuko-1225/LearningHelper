package com.experiment.learinghelper.listActivity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.experiment.learinghelper.R
import kotlin.concurrent.thread

class ListAdapter(private val list:List<ListData>,private val listDao: ListDao,private val done:Boolean):
    RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    private val map = HashMap<Int,Boolean>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title:TextView = view.findViewById(R.id.title)
        val date:TextView = view.findViewById(R.id.date)
        val mCheckBox:CheckBox = view.findViewById(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.date.text = "${item.month+1}月${item.day}日"
        holder.title.text = item.comment
        if(done) {
            holder.mCheckBox.isEnabled = false
            holder.mCheckBox.isChecked = true
            holder.date.setTextColor(Color.parseColor("#ffcccccc"))
            holder.title.setTextColor(Color.parseColor("#ffcccccc"))
        } else {
            holder.mCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    map[position] = true
                    item.done = true
                } else {
                    map.remove(position)
                    item.done = false
                }
                thread {
                    listDao.updateUser(item)
                }
            }
            holder.mCheckBox.isChecked = map.containsKey(position)
        }
    }

}