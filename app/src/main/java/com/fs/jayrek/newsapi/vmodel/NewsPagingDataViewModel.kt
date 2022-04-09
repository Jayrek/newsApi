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
import com.fs.jayrek.newsapi.model.repository.NewsPagingSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsPagingDataViewModel @Inject constructor(private val apiInterface: ApiInterface) :
    ViewModel() {

    fun getNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = NEWS_PAGE_SIZE)
        ) {
            NewsPagingSourceRepository(apiInterface)
        }.flow.cachedIn(viewModelScope)
    }

}