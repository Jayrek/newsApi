package com.fs.jayrek.newsapi.model.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fs.jayrek.newsapi.BuildConfig
import com.fs.jayrek.newsapi.model.helper.Constants
import com.fs.jayrek.newsapi.model.model.Article

class GetNewsByCategoryRepository(
    private val country: String,
    private val category: String,
    private val apiInterface: ApiInterface
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageIndex = params.key ?: Constants.NEWS_STARTING_INDEX
        try {
            val response = apiInterface.getNewsByCategory(
                country,
                category,
                pageIndex,
                Constants.NEWS_PAGE_SIZE,
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