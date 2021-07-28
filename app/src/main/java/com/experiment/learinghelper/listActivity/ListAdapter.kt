package com.experiment.learinghelper.listActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.experiment.learinghelper.R
import kotlin.concurrent.thread

class ListAdapter(private val list:List<ListData>,private val listDao: ListDao):
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
        holder.date.text = "${item.month}月${item.day}日"
        holder.title.text = item.comment
        if(item.done) {
            holder.mCheckBox.isSelected = true
            holder.mCheckBox.isEnabled = false
        }
        holder.mCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                map[position] = true
            } else {
                map.remove(position)
            }
        }
        holder.mCheckBox.isChecked = map.containsKey(position)
    }

}