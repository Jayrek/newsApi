package com.fs.jayrek.newsapi.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fs.jayrek.newsapi.R
import com.fs.jayrek.newsapi.databinding.FragmentNewsBinding
import com.fs.jayrek.newsapi.view.adapter.NewsPagingAdapter
import com.fs.jayrek.newsapi.vmodel.NewsPagingDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rView.apply {
            layoutManager = LinearLayoutManager(context)
            newsAdapter = NewsPagingAdapter()
            adapter = newsAdapter
        }
        observers()
    }

    private fun observers() {
        val viewModel: NewsPagingDataViewModel by viewModels()
        lifecycleScope.launchWhenCreated {
            viewModel.getNews().collectLatest {
                Timber.v("news: $it")
                newsAdapter.submitData(it)
            }
        }
    }
}