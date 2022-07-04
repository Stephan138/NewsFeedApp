package com.example.newsfeedapp.db

import com.example.newsfeedapp.model.News

/**
 * Класс для работы с репозиторием новостей
 */
class NewsRepositoryImpl(private val newsDao: NewsDao) : NewsRepository {
    override suspend fun insert(news: News) = newsDao.insert(news)

    override suspend fun getAllNewsId() = newsDao.getAllNewsId()

    override suspend fun getAllNews() = newsDao.getAllNews()

    override suspend fun deleteById(id: String) = newsDao.deleteById(id)
}