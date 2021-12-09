package com.example.testgithubapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testgithubapp.ApplicationClass
import com.example.testgithubapp.databinding.ActivityMainBinding
import com.example.testgithubapp.model.adapter.GitDataListAdapter
import com.example.testgithubapp.viewModel.MainViewModel
import androidx.core.view.MenuItemCompat

import android.R
import android.os.Handler
import android.os.Looper
import android.view.*

import androidx.appcompat.widget.SearchView

/*
*  Developed By -> Vidhey M B
*  Created Date -> 08/12/2021
*
*  Architecture Pattern -> MVVM
*  Language -> Kotlin
*  Network Api -> Retrofit
*  Support -> Kotlin Coroutines
*
*  Purpose -> Github daily trending repo list display in android app
*
*  Thanks to all dependency sdk or library developers used in this app.
*
* */


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewmodel: MainViewModel
    private lateinit var gitDataListAdapter: GitDataListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApplicationClass.mCurrentActivity = this

        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gitDataListAdapter = GitDataListAdapter()
        binding.listItemRc.adapter = gitDataListAdapter

        viewmodel.getDataList("daily")

        viewmodel.liveData.observe(this, Observer {
            gitDataListAdapter.updateList(it)
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewmodel.searchLocal(it) }
                return false
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        ApplicationClass.mCurrentActivity = null
    }

}