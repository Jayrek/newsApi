package com.fs.jayrek.newsapi.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.jayrek.newsapi.model.helper.Resource
import com.fs.jayrek.newsapi.model.model.News
import com.fs.jayrek.newsapi.model.repository.EverythingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(private val everythingRepository: EverythingRepository) :
    ViewModel() {

    private val news: MutableLiveData<Resource<News>> = MutableLiveData()
    val everythingNews: LiveData<Resource<News>> = news

    fun getNewsEverything() {

        viewModelScope.launch {
            try {
                news.postValue(Resource.Loading())
                val response = everythingRepository.getEverythingNews("bitcoin", 1, 20)
                news.postValue(response)
            } catch (e: Exception) {
                news.postValue(Resource.Error(e.message.toString()))
            }
        }
    }
}