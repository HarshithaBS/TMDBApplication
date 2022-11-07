package com.happiestminds.tmdbapp

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//step 3 - creating retrofit instance

interface TMDBInterface {

    //url: https://api.themoviedb.org/3/movie/popular?api_key=05b467f69d7b35d6e9d1d9f0230b49b
    @GET("movie/popular") //this will be appended to base_url

    fun getPopularMovies(@Query("api_key") key : String) : Call<PopularMovies> //in the request the query name should be passed as api_key


    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        // step 4 - setup instance
        fun createInstance() : TMDBInterface{
            val retrofit = Retrofit.Builder()   //retrofit object creation
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(TMDBInterface :: class.java)
        }


    }

}