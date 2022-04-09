package com.fs.jayrek.newsapi.model.model

data class News(val status: String? = null, val articles: List<Article>)

data class Article(
    val author: String? = null, val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)
