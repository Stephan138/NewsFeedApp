package com.example.newsfeedapp

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.newsfeedapp.model.News
import kotlinx.android.synthetic.main.activity_browser.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_news.*

class BrowserActivity : AppCompatActivity() {
    private var ourFontSize = 14f
    override fun onCreate(savedInstanceState: Bundle?) {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        val url = intent.getStringExtra("url") ?: ""

        val browser: WebView = findViewById(R.id.webBrowser)
        browser.loadUrl(url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.browser_settings, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        when(item.itemId){

            // increase decrease text on browser
            R.id.increase_text -> {
                ourFontSize += 2f
                news_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontSize)

            }
            R.id.decrease_text -> {
                ourFontSize -= 2f
                news_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontSize)
            }

            R.id.red_background -> {
                webBrowser.setBackgroundColor(Color.RED)
                return true
            }
            R.id.white_background -> {
                webBrowser.setBackgroundColor(Color.WHITE)
                return true
            }
            R.id.green_background -> {
                webBrowser.setBackgroundColor(Color.GREEN)
                return true
            }
            R.id.yellow_background -> {
                webBrowser.setBackgroundColor(Color.YELLOW)
                return true
            }
            R.id.gray_background -> {
                webBrowser.setBackgroundColor(Color.GRAY)
                return true
            }
            R.id.blue_background -> {
                webBrowser.setBackgroundColor(Color.BLUE)
                return true
            }

            // set color of actionbar browser screen
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

}