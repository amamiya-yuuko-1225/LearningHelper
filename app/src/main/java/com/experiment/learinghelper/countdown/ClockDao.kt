package com.experiment.learinghelper.countdown

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClockDao {
    @Insert
    fun insertCock(clock: Clock)

    @Query("select * from clock")
    fun loadAllClocks(): List<Clock>

    @Query("select * from clock where year = :year and month = :month and day = :day and isSucceeded = :isSucceeded")
    fun queryClockByDate(year: Int, month: Int, day: Int, isSucceeded: Boolean): List<Clock>

    @Update
    fun updateClock(clock: Clock)
}