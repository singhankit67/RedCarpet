package com.example.redcarpet

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NewsService {
    @GET("top-headlines?country=in&apiKey=71a46c4dac0f4761becf0206e7d67f1f")
    fun getTopHeadlines():Single<News>
}