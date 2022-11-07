package com.happiestminds.tmdbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rView : RecyclerView
    lateinit var pBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = findViewById(R.id.recycleR)
        pBar = findViewById(R.id.pBar)
        rView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        //step 5 - process response data

        val key = resources.getString(R.string.api_key)
        Log.d("MainActivity", "ApiKey : $key")

        val request = TMDBInterface.createInstance().getPopularMovies(key)
        request.enqueue(ResponseCallback()) // asynchronous execution with enqueue
    }

    inner class ResponseCallback : Callback<PopularMovies>{
        override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
            Log.d("MainActivity", "onResponse executed")
            pBar.visibility = View.INVISIBLE
            if (response.isSuccessful){
                response.body()?.let {
                    Log.d("MainActivity", "Movie count : ${it.results.size}")

                    rView.adapter = MovieAdapter(it.results){
                        val intent = Intent(this@MainActivity, ClickActivity() ::class.java)
                        intent.putExtra("movie", it)
                        startActivity(intent)

                    }
                }
            }
            else{
                Toast.makeText(this@MainActivity, "there seems some problem, please try again later", Toast.LENGTH_LONG).show()
            }
        }

        override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
            Toast.makeText(this@MainActivity, "Could not reach TMDB server, try again later", Toast.LENGTH_LONG).show()
            Log.d("MainActivity", "Failure msg : ${t.localizedMessage}") //exact reason is known by using throwable object t
            pBar.visibility = View.INVISIBLE

        }

    }

}