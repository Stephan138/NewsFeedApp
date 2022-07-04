package com.example.newsfeedapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeedapp.model.News.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class News(
    @PrimaryKey
    val id: String,
    val title: String?= null,
    val img: String? = null,
    val local_img: String? = null,
    val news_date: String? = null,
    val annotation: String? = null,
    val id_resource: String? = null,
    val news_date_uts: String? = null,
    val mobile_url: String? = null
){
    companion object{
        const val TABLE_NAME = "News"

        const val ID = "Id"
        const val TITLE = "Title"
        const val IMG = "Img"
        const val LOCAL_IMG = "Local_img"
        const val NEWS_DATE = "News_date"
        const val ANNOTATION = "Annotation"
        const val ID_RESOURCE = "Id_resource"
        const val NEWS_DATE_UTS = "News_date_uts"
        const val MOBILE_URL = "Mobile_url"
    }
}