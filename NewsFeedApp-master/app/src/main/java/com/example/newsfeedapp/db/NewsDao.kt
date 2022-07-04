package com.example.newsfeedapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsfeedapp.model.News

/**
 * Интерфейс для работы с данными
 */
@Dao
interface NewsDao {
    /**
     * Запись новостей
     */
    @Insert
    suspend fun insert(news: News)

    /**
     * Получение всех id новостей
     */
    @Query("SELECT ${News.ID} FROM ${News.TABLE_NAME}")
    suspend fun getAllNewsId(): List<String>

    /**
     * Получение всех новостей
     */
    @Query("SELECT * FROM ${News.TABLE_NAME}")
    suspend fun getAllNews(): List<News>

    /**
     * Удаление по id
     */
    @Query("DELETE FROM ${News.TABLE_NAME} WHERE ${News.ID} = :id")
    suspend fun deleteById(id: String)
}