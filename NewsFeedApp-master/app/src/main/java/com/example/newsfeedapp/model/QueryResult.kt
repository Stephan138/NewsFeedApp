package com.example.newsfeedapp.model

/**
 * @param[success] Успешность формирования запроса.
 * @param[data] Данные от сервиса.
 */
data class QueryResult(val success: Boolean?, val data: Data?)