package com.fs.jayrek.newsapi.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fs.jayrek.newsapi.R
import com.fs.jayrek.newsapi.databinding.FragmentSearchBinding
import com.fs.jayrek.newsapi.databinding.FragmentSettingsBinding
import com.fs.jayrek.newsapi.view.adapter.ViewPagerAdapter

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        val fragments: ArrayList<Fragment> = arrayListOf(
            NewsFragment(),
            SearchFragment()
        )
        val adapter = ViewPagerAdapter(fragments, requireActivity())
        binding.viewPager.adapter = adapter

        return binding.root
    }
}