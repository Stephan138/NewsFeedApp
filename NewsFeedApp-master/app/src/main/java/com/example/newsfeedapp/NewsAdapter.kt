package com.example.newsfeedapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedapp.model.News

/**
 * Адаптер RecyclerView
 */
class NewsAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<NewsViewHolder>() {
    /**
     * Список для отображения
     */

    private val newsList = mutableListOf<News>()

    /**
     * Добавляет данные в компонент
     */
    @SuppressLint("NotifyDataSetChanged")
    fun addNews(news: News) {
        newsList.add(news)
        // TODO: менять конкретные позиции с помощью notifyItemChanged(position)
        notifyDataSetChanged()
    }

    /**
     * Обновляет данные в компоненте
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setNews(news: List<News>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    /**
     * Вызывается при создании компонента
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(itemView)
    }

    /**
     * Устанавливает число элементов в компоненте
     */
    override fun getItemCount() = newsList.size

    /**
     * Заполняет данные в ячейке
     */
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])

        holder.cardView.setOnClickListener {
            onClickListener.onClicked(newsList[position].mobile_url)
        }
    }
}