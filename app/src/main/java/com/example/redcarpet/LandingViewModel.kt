package com.example.redcarpet

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

class LandingViewModel @ViewModelInject constructor(newsRepository: NewsRepository) : ViewModel() {
    private val _topHeadlines = MutableLiveData<List<Article1>>()
    val topHeadlines: LiveData<List<Article1>>
        get() = _topHeadlines
    init {
        newsRepository.getTopHeadlines()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({news -> _topHeadlines.value = news.article},{t -> Timber.e(t)})


    }
}