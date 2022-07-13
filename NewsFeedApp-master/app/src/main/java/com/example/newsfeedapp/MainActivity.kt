package com.example.newsfeedapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeedapp.model.News
import com.example.newsfeedapp.model.QueryResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_news.*
import kotlinx.android.synthetic.main.item_news.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import androidx.core.view.forEach


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var ourFontSize = 14f
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        initRecyclerView(this)
        // Обработчик нажатия на FloatingActionButton
        refreshNewsButton.setOnClickListener {
            var serviceResponse: QueryResult
            lifecycleScope.launch(Dispatchers.IO) {
                // Получение ответа от сервиса
                serviceResponse = getQueryResult().getInfo()

                withContext(Dispatchers.Main) {
                    val newsList: List<News> = getNewsList(serviceResponse)
                    val uniqueNews: List<News> = getUniqueNews(newsList)
                    uniqueNews.forEach {
                        newsAdapter.addNews(it)
                    }
                    // Запись новостей в бд
                    uniqueNews.forEach {
                        insertNews(it)
                    }
                }
            }
        }
        // Отображение содержимого бд при создании активити
        retrieveNews()


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_settings, menu)

        return true
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            //set color of button
            R.id.red_button -> {
                refreshNewsButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return true
            }
            R.id.green_button -> {
                refreshNewsButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                return true
            }
            R.id.yellow_button -> {
                refreshNewsButton.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                return true
            }
            R.id.gray_button -> {
                refreshNewsButton.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                return true
            }
            R.id.blue_button -> {
                refreshNewsButton.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                return true
            }
            R.id.white_button -> {
                refreshNewsButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                return true
            }

            // set color of line between two news
            R.id.red_line -> {
                mainLayout.setBackgroundResource(android.R.color.holo_red_dark)
                return true
            }
            R.id.green_line -> {
                mainLayout.setBackgroundResource(android.R.color.holo_green_dark)
                return true
            }
            R.id.yellow_line -> {
                mainLayout.setBackgroundColor(Color.YELLOW)
                return true
            }
            R.id.gray_line -> {
                mainLayout.setBackgroundColor(Color.GRAY)
                return true
            }
            R.id.blue_line -> {
                mainLayout.setBackgroundColor(Color.BLUE)
                return true
            }
            R.id.white_line -> {
                mainLayout.setBackgroundColor(Color.WHITE)
                return true
            }

            // set text annotation color on main screen
            R.id.red_text_annotation -> {
                news_annotation.setTextColor(Color.RED)
                return true
            }
            R.id.green_text_annotation -> {
                news_annotation.setTextColor(Color.GREEN)
                return true
            }
            R.id.gray_text_annotation -> {
                news_annotation.setTextColor(Color.GRAY)
                return true
            }
            R.id.blue_text_annotation -> {
                news_annotation.setTextColor(Color.BLUE)
                return true
            }
            R.id.yellow_text_annotation -> {
                news_annotation.setTextColor(Color.YELLOW)
                return true
            }
            R.id.white_text_annotation -> {
                news_annotation.setTextColor(Color.WHITE)
                return true
            }

            // set color title on main screen
            R.id.red_text_title -> {
                news_title.setTextColor(Color.RED)
                return true
            }
            R.id.green_text_title -> {
                news_title.setTextColor(Color.GREEN)
                return true
            }
            R.id.gray_text_title -> {
                news_title.setTextColor(Color.GRAY)
                return true
            }
            R.id.blue_text_title -> {
                news_title.setTextColor(Color.BLUE)
                return true
            }
            R.id.yellow_text_title -> {
                news_title.setTextColor(Color.YELLOW)
                return true
            }
            R.id.white_text_title -> {
                news_title.setTextColor(Color.WHITE)
                return true
            }


            // background color
            R.id.yellow_background -> {
                background_news.setBackgroundColor(Color.YELLOW)
                return true
            }
            R.id.red_background -> {
                background_news.setBackgroundColor(Color.RED)
                return true
            }
            R.id.green_background -> {
                background_news.setBackgroundDrawable(ColorDrawable(getColor(R.color.green)))
                return true
            }
            R.id.white_background -> {
                background_news.setBackgroundColor(Color.WHITE)
                return true
            }
            R.id.blue_background -> {
                background_news.setBackgroundColor(Color.BLUE)
                return true
            }
            R.id.gray_background -> {
                background_news.setBackgroundColor(Color.GRAY)
                return true
            }


            // increase text on main
            R.id.increase_text -> {
                ourFontSize += 2f
                news_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontSize)

            }
            R.id.decrease_text -> {
                ourFontSize -= 2f
                news_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontSize)
            }
            R.id.increase_text_annotation -> {
                ourFontSize += 2f
                news_annotation.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontSize)
            }
            R.id.decrease_text_annotation -> {
                ourFontSize -= 2f
                news_annotation.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontSize)
            }


            // set color of actionbar main screen
            R.id.green_bar -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(getColor(R.color.green)))
            }
            R.id.blue_bar -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(getColor(android.R.color.holo_blue_light)))
            }
            R.id.white_bar -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(getColor(R.color.white)))
            }
            R.id.yellow_bar -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(getColor(android.R.color.holo_orange_light)))
            }
            R.id.gray_bar -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(getColor(android.R.color.system_accent2_400)))
            }
            R.id.red_bar -> {
                supportActionBar!!.setBackgroundDrawable(ColorDrawable(getColor(android.R.color.holo_red_dark)))
            }
        }
        return true
    }


    /**
     * Иницилазация RecyclerView
     */
    private fun initRecyclerView(app: AppCompatActivity) {
        newsAdapter = NewsAdapter(object : OnClickListener {
            // Обработчик нажатия на ячейку новости
            override fun onClicked(mobile_url: String?) {
                val intent = Intent(app, BrowserActivity::class.java)
                intent.putExtra("url", mobile_url ?: "")
                startActivity(intent)
            }
        })

        with(newsList) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = newsAdapter
            this.setHasFixedSize(true)
        }
    }

    /**
     * Извлечение списка новостей из [qRes].
     * @param [qRes] Ответ от сервиса.
     * @return Пустой список, если ответ пустой.
     */
    private fun getNewsList(qRes: QueryResult) = qRes.data?.news ?: listOf()

    /**
     * Отбирает из [newsList] уникальные новости (которых нет в бд).
     * @param[newsList] Исходный список новостей.
     */
    private suspend fun getUniqueNews(newsList: List<News>): List<News> {
        val idNewsList = (applicationContext as App).repository.getAllNewsId()
        val result = mutableListOf<News>()
        newsList.forEach {
            if (it.id !in idNewsList)
                result.add(it)
        }
        return result.toList()
    }

    /**
     * Запись [news] в бд.
     * @param [news] Новость.
     */
    private fun insertNews(news: News) =
        lifecycleScope.launch(Dispatchers.IO) {
            (applicationContext as App).repository.insert(news = news)
        }

    /**
     * Извлечение и отображение новостей на RecyclerView
     */
    private fun retrieveNews() =
        lifecycleScope.launch(Dispatchers.IO) {
            val newsList = (applicationContext as App).repository.getAllNews()
            withContext(Dispatchers.Main) {
                newsAdapter.setNews(newsList)
            }
        }
}