package com.example.redcarpet

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsService : NewsService){
    fun getTopHeadlines():Single<News>{
        return newsService.getTopHeadlines()
            .subscribeOn(Schedulers.io())
    }
}