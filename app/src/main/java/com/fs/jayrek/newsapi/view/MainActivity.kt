package com.fs.jayrek.newsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.jayrek.newsapi.R
import com.fs.jayrek.newsapi.databinding.ActivityMainBinding
import com.fs.jayrek.newsapi.model.helper.Resource
import com.fs.jayrek.newsapi.model.model.Article
import com.fs.jayrek.newsapi.view.adapter.NewsAdapter
import com.fs.jayrek.newsapi.view.adapter.NewsPagingAdapter
import com.fs.jayrek.newsapi.vmodel.EverythingViewModel
import com.fs.jayrek.newsapi.vmodel.NewsPagingDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsPagingAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rView.apply {
            layoutManager = LinearLayoutManager(context)
            newsAdapter = NewsPagingAdapter()
            adapter = newsAdapter
        }


        /*val viewModel: EverythingViewModel by viewModels()

        viewModel.getNewsEverything()
        viewModel.everythingNews.observe(this) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Timber.d("results: ${it.data}")
                    if (it.data?.articles!!.isNotEmpty()) {
                        binding.rView.apply {
                            layoutManager = LinearLayoutManager(context)
                            newsAdapter = NewsAdapter(it.data.articles)
                            adapter = newsAdapter
                        }
                    }
                }
                is Resource.Error -> {}
            }
        }*/


        observers()
    }

    private fun observers() {
        val viewModel: NewsPagingDataViewModel by viewModels()
        lifecycleScope.launchWhenCreated {
            viewModel.getNews().collectLatest {
                newsAdapter.submitData(it)
            }
        }
    }
}