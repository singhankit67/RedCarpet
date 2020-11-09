package com.example.redcarpet

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NewsService {
    @GET("top-headlines?country=in&apiKey=71a46c4dac0f4761becf0206e7d67f1f")
    fun getTopHeadlines():Single<News>
}
//The Single class represents the single value response. Single observable can only emit either a single successful value or an error. It does not emit onComplete event.
