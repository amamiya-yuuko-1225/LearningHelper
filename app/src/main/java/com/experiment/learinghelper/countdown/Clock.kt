package com.experiment.learinghelper.countdown

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clock")
data class Clock(
    val duration: Int = 60,
    @ColumnInfo( name = "isSucceeded")
    val isSucceeded: Boolean,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "month")
    val month: Int,
    @ColumnInfo(name = "day")
    val day: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)
