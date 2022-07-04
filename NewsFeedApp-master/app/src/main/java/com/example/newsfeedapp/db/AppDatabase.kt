package com.example.newsfeedapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsfeedapp.model.News

@Database(
    entities = [News::class], version = 1
)

/**
 * База данных
 */
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        /**
         * Создание базы данных
         */
        fun buildDatabase(context: Context, dbName: String): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
        }
    }

}