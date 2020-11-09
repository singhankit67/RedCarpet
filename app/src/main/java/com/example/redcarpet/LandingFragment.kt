package com.example.redcarpet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.landing_fragment.*
import timber.log.Timber

//@AndroidEntryPoint which means that hilt should provide all the dependencies to this fragment that it asks for.
@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.landing_fragment) {
    private lateinit var newsAdapter:NewsAdapter
    private val landingViewModel by viewModels<LandingViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter()
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = newsAdapter

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        landingViewModel.topHeadlines.observe(viewLifecycleOwner, Observer {
            newsAdapter.setNews(it)

        })

    }
}
