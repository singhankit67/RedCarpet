package com.example.redcarpet

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
//We have used @Inject above the Repository constructor so that whenever we need a Repository object hilt will provide us Repository Object
class NewsRepository @Inject constructor(private val newsService : NewsService){
    fun getTopHeadlines():Single<News>{
        return newsService.getTopHeadlines()
            .subscribeOn(Schedulers.io())
        //this means that we want to subscribe on the background thread
    }
    //so the code doing here is newsService.get() is the observable part which is of type single that is it can emit a single successful or error signal no onComplete
    //and we are doing it on the back ground thread
    
}
