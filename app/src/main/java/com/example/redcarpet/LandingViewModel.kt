package com.example.redcarpet

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

//Here we see a new annotation @ViewModelInject and it is the best thing about using hilt. If we use dagger in our projects with MVVM
//we must have faced the problem of injecting viewmodels. The most famous workaround was to create your own ViewModelFactory class but that was a long and tedious work.
//But with the hilt, this annotation will do all the work and you can easily inject ViewModels.
class LandingViewModel @ViewModelInject constructor(newsRepository: NewsRepository) : ViewModel() {
    private val _topHeadlines = MutableLiveData<List<Article1>>() //this will update the list
     //set the topHeadlines(Mutable Live Data) to update the fragment of the changes.
    val topHeadlines: LiveData<List<Article1>>
        get() = _topHeadlines //getting the list here in another variable
    init {
        newsRepository.getTopHeadlines()
            .observeOn(AndroidSchedulers.mainThread())
        //observeOn(AndroidSchedulers.mainThread()) means that we want to observe the data on the main thread
            .subscribe({news -> _topHeadlines.value = news.article},{t -> Timber.e(t)}) //the value returnde is stored in article
        //a subscription is created here
        //used timber for better logging experience


    }
}
