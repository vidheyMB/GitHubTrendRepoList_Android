package com.example.testgithubapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testgithubapp.baseClass.BaseViewModel
import com.example.testgithubapp.model.GitHubRepositoryItem
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {

    private val gitList = mutableListOf<GitHubRepositoryItem>()
    private val _liveData = MutableLiveData<List<GitHubRepositoryItem>>()
    val liveData: LiveData<List<GitHubRepositoryItem>> = _liveData


    fun getDataList(since: String) {
        if(gitList.isNullOrEmpty())
        ///launch the coroutine scope
        scope.launch {
            //post the value inside live data
            apiRepository.getDataList(since)?.let { gitList.addAll(it) }
            _liveData.postValue(gitList)
        }
    }

    fun searchLocal(userName: String){
        if(userName.isNotEmpty()) {
            val searchList = gitList.filter { it.username!!.contains(userName, true) }
            _liveData.postValue(searchList)
        }else{
            _liveData.postValue(gitList)
        }
    }

}