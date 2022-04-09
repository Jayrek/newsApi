package com.fs.jayrek.newsapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fs.jayrek.newsapi.databinding.ItemNewsBinding
import com.fs.jayrek.newsapi.model.model.Article

class NewsPagingAdapter :
    PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder>(NewsDiffCallBack()) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.getInstance(parent)
    }

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun getInstance(parent: ViewGroup): NewsViewHolder {
                return NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }

        fun bind(item: Article?) {
            binding.news = item
            binding.executePendingBindings()
        }
    }

    class NewsDiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
