package com.example.testgithubapp.baseClass

import androidx.lifecycle.ViewModel
import com.example.testgithubapp.network.RestAPIClass
import com.example.testgithubapp.network.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    val scope = CoroutineScope(coroutineContext)

    //initialize repo
    val apiRepository : ApiRepository = ApiRepository(RestAPIClass.apiInterface)

    // cancel all the request
    fun cancelRequests() = coroutineContext.cancel()

}