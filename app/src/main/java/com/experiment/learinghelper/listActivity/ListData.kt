package com.experiment.learinghelper.listActivity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*
@Entity
class ListData:Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var comment: String = "NaN"
    var done: Boolean = false
    var month: Int = 1
    var day: Int = 1
    var pri:Int = 0
}