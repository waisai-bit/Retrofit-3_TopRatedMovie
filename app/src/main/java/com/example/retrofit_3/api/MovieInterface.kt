package com.example.retrofit_3.api


import com.example.retrofit_3.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface{

//Querystring parameter method

    @GET("top_rated")
    fun getTopRated(
        @Query("api_key")apiKey : String): Call<Movie>

}