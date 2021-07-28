package com.experiment.learinghelper.checkActivity

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableWrapper
import android.util.Log
import com.experiment.learinghelper.R
import com.prolificinteractive.materialcalendarview.CalendarDay

import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class DayAccent(private val listDay: MutableList<CalendarDay>):DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        for(d in listDay) {
            if(day!!.day == d.day && day!!.month == d.month && day!!.year == d.year) {
                //Log.e("judge", "shouldDecorate: get" )
                return true
            }
        }
        return false
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(DotSpan())
    }
}