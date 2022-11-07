package com.happiestminds.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ClickActivity : AppCompatActivity() {

    lateinit var overview : TextView
    lateinit var image : ImageView
    lateinit var releaseDate : TextView
    lateinit var rating : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click)

        overview = findViewById(R.id.overviewT)
        image = findViewById(R.id.imgV)
        releaseDate = findViewById(R.id.releaseT)
        rating = findViewById(R.id.rateT)


        val selectedMovie = intent.getParcelableExtra<Movies>("movie")
        val imgpath = "https://image.tmdb.org/t/p/w500${selectedMovie?.poster_path}"
        Glide.with(this).load(imgpath).into(image)
        overview.text = selectedMovie?.overview
        releaseDate.text = selectedMovie?.release_date
        rating.text = selectedMovie?.vote_average.toString()

    }
}