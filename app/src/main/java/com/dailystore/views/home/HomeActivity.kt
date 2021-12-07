package com.dailystore.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dailystore.R
import com.dailystore.databinding.ActivityHomeBinding
import com.dailystore.viewmodels.home.HomeViewModel
import com.dailystore.viewmodels.home.HomeViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
    }
}