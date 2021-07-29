package com.experiment.learinghelper.daily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ArrayAdapter
import com.experiment.learinghelper.R
import com.experiment.learinghelper.countdown.Clock
import com.experiment.learinghelper.database.AppDatabase
import com.experiment.learinghelper.listActivity.ListData
import kotlinx.android.synthetic.main.activity_daily.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class DailyActivity : AppCompatActivity() {
    companion object {
        lateinit var handler: Handler
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        val clockDao = AppDatabase.getDatabase(this).clockDao()
        val listDao = AppDatabase.getDatabase(this).listDao()
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        var succeededToday: List<Clock> = ArrayList()
        var failedToday: List<Clock> = ArrayList()
        var timeToday: Int = 0

        var listsDoneToday: List<ListData> = ArrayList()
        var listaUndoToday: List<ListData> = ArrayList()

        thread {
            succeededToday = clockDao.queryClockByDate(year, month, day ,true)
            failedToday = clockDao.queryClockByDate(year, month, day, false)
            for(it in succeededToday){
                timeToday += it.duration
            }
            listsDoneToday = listDao.selectDone()
            listaUndoToday = listDao.selectUndo()

            val msg = Message()
            msg.what = 10
            handler.sendMessage(msg)
        }

        


        handler = object: Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    10 -> {
                        weekly_number_of_times.text = succeededToday.size.toString()
                        weekly_number_of_times_failed.text = failedToday.size.toString()
                        weekly_time.text = timeToday.toString()

                        var adapter1 = ArrayAdapter(applicationContext, R.layout.daily_item, listsDoneToday)
                        var adapter2 = ArrayAdapter(applicationContext, R.layout.daily_item, listaUndoToday)
                        daily_finished_list_view.adapter = adapter1
                        daily_failed_list_view.adapter = adapter2
                    }
                }
            }
        }


    }
}