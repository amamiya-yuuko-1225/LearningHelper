package com.experiment.learinghelper.listActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.experiment.learinghelper.R
import com.experiment.learinghelper.database.AppDatabase
import kotlinx.android.synthetic.main.activity_list.*
import kotlin.concurrent.thread

class ListActivity : AppCompatActivity() {
    private lateinit var listDao:ListDao
    private lateinit var listUndo:MutableList<ListData>
    private lateinit var listDone:MutableList<ListData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listDao = AppDatabase.getDatabase(this).listDao()
        initUI()
    }
    private fun initUI() {
        //supportActionBar!!.hide()
        initList()
        add.setOnClickListener {
            startActivityForResult(Intent(this,EditListActivity::class.java)
                ,1)
        }
    }

    private fun initList() {
        thread {
            listUndo = listDao.selectUndo() as MutableList<ListData>
            listDone = listDao.selectDone() as MutableList<ListData>
            Log.e("room", "initListUndo:${listUndo.size} " )
            Log.e("room", "initListDone:${listDone.size} " )
            recyclerView_listUndo.let {
                it.layoutManager = LinearLayoutManager(this)
                it.adapter = ListAdapter(listUndo,listDao,false)
                Log.e("undo", "${it.adapter!!.itemCount}")
            }
            recyclerView_listDone.let {
                it.layoutManager = LinearLayoutManager(this)
                it.adapter = ListAdapter(listDone,listDao,true)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            1 -> if(resultCode == Activity.RESULT_OK) {
                val list = data!!.getSerializableExtra("list") as ListData
                if(list.done) {
                    listDone.add(list)
                    recyclerView_listDone.adapter!!.notifyDataSetChanged()
                } else {
                    listUndo.add(list)
                    recyclerView_listUndo.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }



}