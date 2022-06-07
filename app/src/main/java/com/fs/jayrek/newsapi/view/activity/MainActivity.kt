package com.fs.jayrek.newsapi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fs.jayrek.newsapi.R
//import com.fs.jayrek.newsapi.databinding.ActivityMainBinding
import com.fs.jayrek.newsapi.view.adapter.NewsPagingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsPagingAdapter
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding =
//            DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.rView.apply {
//            layoutManager = LinearLayoutManager(context)
//            newsAdapter = NewsPagingAdapter()
//            adapter = newsAdapter
//        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_host_main)

        val appBarConfig =
            AppBarConfiguration(setOf(R.id.newsFragment, R.id.searchFragment, R.id.settingsFragment))
        setupActionBarWithNavController(navController, appBarConfig)

        bottomNavigationView.setupWithNavController(navController)
    }


}