package com.fs.jayrek.newsapi.model.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fs.jayrek.newsapi.BuildConfig
import com.fs.jayrek.newsapi.model.helper.Constants.Companion.NEWS_PAGE_SIZE
import com.fs.jayrek.newsapi.model.helper.Constants.Companion.NEWS_STARTING_INDEX
import com.fs.jayrek.newsapi.model.model.Article

class NewsPagingSourceRepository(private val apiInterface: ApiInterface) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageIndex = params.key ?: NEWS_STARTING_INDEX
        try {
            val response = apiInterface.getEverythingNews(
                "nba",
                pageIndex,
                NEWS_PAGE_SIZE,
                BuildConfig.NEWS_API_KEY
            )

            return LoadResult.Page(
                data = response.articles,
                prevKey = pageIndex - 1,
                nextKey = pageIndex + 1
            )


        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}