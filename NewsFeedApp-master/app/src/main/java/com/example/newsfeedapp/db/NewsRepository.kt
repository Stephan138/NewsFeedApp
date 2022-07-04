package com.example.newsfeedapp.db

import com.example.newsfeedapp.model.News

/**
 * Интерфейс для работы с репозиторием новостей
 */
interface NewsRepository {
    /**
     * Запись новостей
     */
    suspend fun insert(news: News)

    /**
     * Получение всех id новостей
     */
    suspend fun getAllNewsId(): List<String>

    /**
     * Получение всех новостей
     */
    suspend fun getAllNews(): List<News>

    /**
     * Удаление по id
     */
    suspend fun deleteById(id: String)
}