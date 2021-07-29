package com.experiment.learinghelper.listActivity

import androidx.room.*

@Dao
interface ListDao {
    @Insert
    fun insertList(listData: ListData):Long
    @Query("select * from ListData")
    fun loadAllLists(): List<ListData>
    @Delete
    fun deleteList(listData: ListData)
    @Update
    fun updateUser(listData: ListData)
    @Query("select * from ListData where done = 0 order by pri desc")
    fun selectUndo():List<ListData>
    @Query("select * from ListData where done = 1 order by pri desc")
    fun selectDone():List<ListData>

}