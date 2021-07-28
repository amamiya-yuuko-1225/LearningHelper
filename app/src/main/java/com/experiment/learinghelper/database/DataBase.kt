package com.experiment.learinghelper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.experiment.learinghelper.listActivity.ListDao
import com.experiment.learinghelper.listActivity.ListData

@Database(version = 1, entities = [ListData::class])
abstract  class AppDatabase:RoomDatabase() {
    abstract fun listDao():ListDao
    companion object {
        private var instance: AppDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app_database")
                .build().apply {
                    instance = this
                }
        }
    }

}