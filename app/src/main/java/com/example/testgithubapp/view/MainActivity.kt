package com.example.testgithubapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testgithubapp.ApplicationClass
import com.example.testgithubapp.databinding.ActivityMainBinding
import com.example.testgithubapp.model.adapter.GitDataListAdapter
import com.example.testgithubapp.viewModel.MainViewModel

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

    }

    override fun onDestroy() {
        super.onDestroy()
        ApplicationClass.mCurrentActivity = null
    }

}