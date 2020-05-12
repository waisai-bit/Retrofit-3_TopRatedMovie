package com.example.retrofit_3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_3.api.MovieApi
import com.example.retrofit_3.model.Movie
import retrofit2.Call
import retrofit2.Callback
import com.example.retrofit_3.model.Result
import retrofit2.Response


class MovieViewModel : ViewModel() {

    var topRated: MutableLiveData<List<Result>> = MutableLiveData()
    var LoadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getTopRated(apiKey: String): LiveData<List<Result>> = topRated
    fun getError(): LiveData<Boolean> = LoadError
    fun getLoading(): LiveData<Boolean> = loading

    private val movieApi: MovieApi = MovieApi()

    fun loadMovie() {
        loading.value = true
        val apiCall = movieApi.getTopRated( "7cd41f7e39674ff1d947d80a5641c0ad")

        apiCall.enqueue(object : Callback<Movie> {

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                println("Internet Connection is needed")
                LoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                println("RESPONSE ${response.body().toString()}")
                response.isSuccessful.let {
                    loading.value = false
                    val resultList = response.body()?.results ?: emptyList()
                    topRated.value = resultList
                }
            }

        })
    }

}
