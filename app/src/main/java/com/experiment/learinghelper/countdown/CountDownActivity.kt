package com.experiment.learinghelper.countdown

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.experiment.learinghelper.R
import com.experiment.learinghelper.database.AppDatabase
import kotlinx.android.synthetic.main.activity_count_down.*
import java.util.*
import kotlin.concurrent.thread

class CountDownActivity : AppCompatActivity() {
    companion object {
        lateinit var handler: Handler
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)

        var succeededClocks = 0
        var failedClocks = 0

        val clockDao = AppDatabase.getDatabase(this).clockDao()
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        var isLearning: Boolean = false
        val isSucceeded: Boolean = false
        var totalTime:Int = 2
        var subTotalTime: Int = totalTime*60*1000


        thread{
            succeededClocks = clockDao.queryClockByDate(year, month, day, true).size
            failedClocks = clockDao.queryClockByDate(year, month, day, false).size
            val msg = Message()
            msg.what = 1
            handler.sendMessage(msg)
        }

        val intent = intent
        totalTime = intent.getIntExtra("time", 2)
        subTotalTime = totalTime*1000*60

        // 设置属性动画
        val countDownView: CountDownView = findViewById(R.id.count_down_view)
        countDownView.setTotalTime(subTotalTime)
        val valueAnimator: ValueAnimator = ObjectAnimator.ofFloat(0f, subTotalTime.toFloat())
        valueAnimator.interpolator = LinearInterpolator() // 线性插值器，匀速变化
        valueAnimator.duration = subTotalTime.toLong() // 执行时间

        valueAnimator.addUpdateListener { animation ->
            val currentTime = animation.animatedValue as Float
            countDownView.setCurrentTime(currentTime.toInt())
        }

        valueAnimator.doOnEnd {
            if (countDownView.timeLeft == 0){
                Toast.makeText(applicationContext, "成功完成", Toast.LENGTH_SHORT).show()
                succeededClocks += 1
                number_of_tasks.text = succeededClocks.toString()
                thread {
                    val clock = Clock(subTotalTime, true, year, month, day)
                    clockDao.insertCock(clock)
                }
            }else{
                Toast.makeText(applicationContext, "任务放弃", Toast.LENGTH_SHORT).show()
                failedClocks += 1
                number_of_tasks_failed.text = failedClocks.toString()
                thread {
                    val clock = Clock(subTotalTime, false, year, month, day)
                    clockDao.insertCock(clock)
                }
            }
        }

        count_down_button.setBackgroundColor(Color.parseColor("#FFFFFF"))
        count_down_button.setOnClickListener {
            if (isLearning){
                count_down_button.setImageResource(R.drawable.start)
                isLearning = false
                valueAnimator.end()
            }else{
                count_down_button.setImageResource(R.drawable.doing)
                isLearning = true
                valueAnimator.start()
            }
        }

        handler = object: Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                when(msg.what){
                    1 -> {
                        number_of_tasks.text = succeededClocks.toString()
                        number_of_tasks_failed.text = failedClocks.toString()
                    }
                }
            }
        }

        fab1.setOnClickListener {
            val intent = Intent(this, EditClockActivity::class.java)
            startActivity(intent)
        }
    }
}