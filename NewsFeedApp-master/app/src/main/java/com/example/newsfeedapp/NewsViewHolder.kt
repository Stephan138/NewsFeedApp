package com.example.newsfeedapp

import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedapp.model.News
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * ViewHolder для RecyclerView
 */
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Ссылка на элемент RecyclerView
     */
    val cardView: CardView = itemView.findViewById<View>(R.id.cardView) as CardView

    /**
     * Связывает данные с компонентом RecyclerView
     */
    fun bind(news: News) {
        with(itemView) {
            news.run {
                news_title.text = title
                news_annotation.text = annotation
            }
        }
    }
}