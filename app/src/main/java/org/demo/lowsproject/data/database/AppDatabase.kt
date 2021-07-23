package org.demo.lowsproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.demo.lowsproject.data.dao.TvShowDao
import org.demo.lowsproject.data.dbo.TvShowDbo

@Database(entities = arrayOf(TvShowDbo::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tvShowDao(): TvShowDao
}