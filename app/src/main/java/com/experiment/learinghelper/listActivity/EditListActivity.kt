package com.experiment.learinghelper.listActivity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import com.experiment.learinghelper.R
import com.experiment.learinghelper.database.AppDatabase
import kotlinx.android.synthetic.main.activity_edit_list.*
import java.util.*
import kotlin.concurrent.thread

class EditListActivity : AppCompatActivity() {
    private var comment = ""
    private var mon = 1
    private var day = 1
    private var pri:Int = 0
    private var done = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_list)
        initUI()
    }

    private fun initUI() {
        back.setOnClickListener {
            onBackPressed()
        }

        val c: Calendar = Calendar.getInstance()
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        val month: Int = c.get(Calendar.MONTH)
        val year: Int = c.get(Calendar.YEAR)

        date_pick.setOnClickListener {
            DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { _, _, month, dayOfMonth ->
                    mon = month
                    this.day = dayOfMonth
                }
                ,year,month,day).show()
        }
        priority.setOnClickListener {
            var menu = PopupMenu(this,priority)
            menu.menuInflater.inflate(R.menu.priority_menu,menu.menu)
            menu.setOnMenuItemClickListener{
                pri = when(it.itemId) {
                    R.id.high -> 3
                    R.id.medium -> 2
                    R.id.low -> 1
                    R.id.none -> 0
                    else -> 0
                }
                return@setOnMenuItemClickListener true
            }
            menu.show()
        }

        checkBoxEdit.setOnCheckedChangeListener { _, isChecked ->
            done = isChecked
        }
    }

    override fun onBackPressed() {
        comment = edit.text.toString()
        if(comment.isNotEmpty())
            save()
        else
            setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }

    private fun save() {
        Log.e("save", "save:ok " )
        val list = ListData()
        list.let {
            it.month = mon
            it.day = day
            it.comment = comment
            it.done = done
            Log.e("selected", "${checkBoxEdit.isSelected} " )
            it.pri = pri
        }
        thread {
            val listDao = AppDatabase.getDatabase(this).listDao()
            listDao.insertList(list)
            Log.e("edit", "saveDone: ${listDao.selectDone().size}" )
            Log.e("edit", "saveUndo: ${listDao.selectUndo().size}" )
        }
        setResult(Activity.RESULT_OK,Intent().putExtra("list",list))
    }
}