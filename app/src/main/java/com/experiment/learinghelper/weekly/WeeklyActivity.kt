package com.experiment.learinghelper.weekly

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.experiment.learinghelper.MainActivity
import com.experiment.learinghelper.R
import com.experiment.learinghelper.countdown.Clock
import com.experiment.learinghelper.database.AppDatabase
import kotlinx.android.synthetic.main.activity_count_down.*
import kotlinx.android.synthetic.main.activity_weekly.*
import kotlinx.android.synthetic.main.activity_weekly.back
import lecho.lib.hellocharts.model.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


class WeeklyActivity : AppCompatActivity() {
    companion object {
        lateinit var handler: Handler
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val clockDao = AppDatabase.getDatabase(this).clockDao()
        val calendar = Calendar.getInstance()
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH)
        var year = calendar.get(Calendar.YEAR)
        var dataOfThisWeek = arrayOf(0, 0, 0, 0, 0, 0 ,0)
        var timeThisWeek = 0
        var dataOfLastWeek = arrayOf(0, 0, 0, 0, 0, 0, 0)
        var timeLastWeek = 0

        var valuesThisWeek: MutableList<PointValue> = ArrayList()
        var valuesLastWeek: MutableList<PointValue> = ArrayList()




        thread{
            //获取本周数据
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
                calendar.add(Calendar.DAY_OF_WEEK, -1)
                day = calendar.get(Calendar.DAY_OF_MONTH)
                month = calendar.get(Calendar.MONTH)
                year = calendar.get(Calendar.YEAR)
            }
            for(i in 0..6){
                val dailyList: List<Clock> = clockDao.queryClockByDate(year, month, day, true)

                //统计每天数据
                for (it in dailyList){
                    dataOfThisWeek[i] += it.duration
                    timeThisWeek += it.duration
                }
                valuesThisWeek.add(PointValue((i + 1 ).toFloat(), dataOfThisWeek[i].toFloat()/50))
            }

            //往前推7天
            calendar.add(Calendar.DATE, -7)
            for(i in 0..6){
                val dailyList: List<Clock> = clockDao.queryClockByDate(year, month, day, true)

                //统计每天数据
                for (it in dailyList){
                    dataOfLastWeek[i] += it.duration
                    timeLastWeek += it.duration
                }
                valuesLastWeek.add(PointValue((i + 1 ).toFloat(), dataOfThisWeek[i].toFloat()/50))
            }

            val msg = Message()
            msg.what = 123
            handler.sendMessage(msg)
        }

        handler = object: Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    123 -> {
                        val dataX = arrayOf(
                            "周一",
                            "周二",
                            "周三",
                            "周四",
                            "周五",
                            "周六",
                            "周日"
                        )

                        val line1: Line = Line(valuesThisWeek).setColor(Color.BLUE).setCubic(true)
                        val line2: Line = Line(valuesLastWeek).setColor(Color.GRAY).setCubic(true)
                        val lines: MutableList<Line> = ArrayList<Line>()
                        lines.add(line1)
                        lines.add(line2)
                        val data = LineChartData()
                        data.lines = lines

                        val axisX = Axis().setHasLines(true)
                        val axisY = Axis().setHasLines(true)
                        axisX.name = ""
                        axisY.name = "时长(min)"
                        data.axisXBottom = axisX
                        data.axisYLeft = axisY


                        val xAxisValues: ArrayList<AxisValue> = ArrayList()
                        for(i in 0..6){
                            xAxisValues.add(AxisValue((i+1).toFloat()).setLabel(dataX[i]))
                        }
                        axisX.values = xAxisValues

                        val yAxisValues: ArrayList<AxisValue> = ArrayList()
                        val yValues = intArrayOf(50,100,150,200)
                        for (i in 0..3){
                            yAxisValues.add(AxisValue((i+1).toFloat()).setLabel(yValues[i].toString()))
                        }
                        //val axisValues: MutableList<AxisValue> = ArrayList()
//                        for (i in 0 until scoreRange.size - 1) {
//                            val text = scoreRange[i].toString() + "~" + scoreRange[i + 1]
//                            axisValues.add(AxisValue(i.toFloat(), text.toCharArray()))
//                        }
//                        axisY.values = axisValues

                        //val chart = LineChartView(applicationContext)
                        chart.lineChartData = data

                    }
                }
            }
        }
    }
}