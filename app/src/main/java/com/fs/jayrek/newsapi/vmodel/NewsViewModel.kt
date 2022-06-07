package com.fs.jayrek.newsapi.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fs.jayrek.newsapi.model.helper.Constants.Companion.NEWS_PAGE_SIZE
import com.fs.jayrek.newsapi.model.model.Article
import com.fs.jayrek.newsapi.model.repository.ApiInterface
import com.fs.jayrek.newsapi.model.repository.GetNewsByCategoryRepository
import com.fs.jayrek.newsapi.model.repository.SearchNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val apiInterface: ApiInterface) :
    ViewModel() {

    fun getNews(keyWord: String): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = NEWS_PAGE_SIZE)
    ) { SearchNewsRepository(keyWord, apiInterface) }.flow.cachedIn(viewModelScope)

    fun getNewsByCategory(country: String, category: String): Flow<PagingData<Article>> =
        Pager(config = PagingConfig(pageSize = NEWS_PAGE_SIZE)) {
            GetNewsByCategoryRepository(country, category, apiInterface)
        }.flow.cachedIn(viewModelScope)

}