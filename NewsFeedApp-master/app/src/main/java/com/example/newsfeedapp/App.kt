package com.example.newsfeedapp

import android.app.Application
import com.example.newsfeedapp.db.AppDatabase
import com.example.newsfeedapp.db.NewsRepository
import com.example.newsfeedapp.db.NewsRepositoryImpl

class App : Application() {
    private lateinit var database: AppDatabase

    lateinit var repository: NewsRepository

    override fun onCreate() {
        super.onCreate()

        database = AppDatabase.buildDatabase(
            applicationContext,
            DATABASE_NAME
        )

        repository = NewsRepositoryImpl(database.newsDao())
    }

    companion object {
        private const val DATABASE_NAME = "news_feed_app_database.db"
    }
}