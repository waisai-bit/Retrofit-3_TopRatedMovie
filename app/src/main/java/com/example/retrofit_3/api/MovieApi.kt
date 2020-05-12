package com.example.retrofit_3.api

import com.example.retrofit_3.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {

    private val movieInterface: MovieInterface

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }
//creating the instance of movie_interface obj
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieInterface = retrofit.create(
            MovieInterface::class.java)
    }

    fun getTopRated(apiKey: String): Call<Movie> {
        return movieInterface.getTopRated(apiKey)
    }
}