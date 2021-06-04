package com.example.weatherkotlin.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherkotlin.R
import com.example.weatherkotlin.databinding.MainActivityBinding
import com.example.weatherkotlin.databinding.MainActivityWebviewBinding
import com.example.weatherkotlin.view.main.MainFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: MainActivityWebviewBinding
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = MainActivityWebviewBinding.inflate(layoutInflater)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.ok.setOnClickListener(clickListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
/*
    var clickListener: View.OnClickListener = object : View.OnClickListener {

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onClick(v: View?) {
            try {
                val uri = URL(binding.url.text.toString())
                val handler = Handler()
                Thread {
                    var urlConnection: HttpsURLConnection? = null
                    try {
                        urlConnection = uri.openConnection() as HttpsURLConnection
                        urlConnection.requestMethod = "GET"
                        urlConnection.readTimeout = 10000
                        val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                        val result = getLines(reader)
                        handler.post {
                            binding.webview.loadData(result, "text/html; charter=utf-8", "utf-8")
                        }
                    } catch (e: Exception) {
                        Log.e("", "Fail connection", e)
                        e.printStackTrace()
                    } finally {
                        urlConnection?.disconnect()
                    }
                }.start()
            } catch (e: MalformedURLException) {
                Log.e("", "Fail URI", e)
                    e.printStackTrace()
            }
        }

        @RequiresApi(Build.VERSION_CODES.N)
        private fun getLines(reader: BufferedReader): String {
            return reader.lines().collect(Collectors.joining("\n"))
        }
    }
*/
}