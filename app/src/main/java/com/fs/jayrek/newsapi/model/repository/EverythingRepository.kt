package com.fs.jayrek.newsapi.model.repository

import com.fs.jayrek.newsapi.BuildConfig
import com.fs.jayrek.newsapi.model.helper.Resource
import com.fs.jayrek.newsapi.model.helper.safeApiCall
import com.fs.jayrek.newsapi.model.model.News
import javax.inject.Inject

class EverythingRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getEverythingNews(keyword: String, page: Int, pageSize: Int): Resource<News> {
        return safeApiCall {
            Resource.Success(
                apiInterface.getEverythingNews(
                    keyword, page, pageSize,
                    BuildConfig.NEWS_API_KEY
                )
            )
        }
    }
}