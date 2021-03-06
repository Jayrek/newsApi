package com.fs.jayrek.newsapi.model.repository

import com.fs.jayrek.newsapi.model.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("everything?")
    suspend fun getEverythingNews(
        @Query("q") keyword: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): News

    @GET("top-headlines?")
    suspend fun getNewsByCategory(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): News

}