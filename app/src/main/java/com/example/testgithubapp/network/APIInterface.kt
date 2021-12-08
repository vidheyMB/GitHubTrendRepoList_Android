package com.example.testgithubapp.network

import com.example.testgithubapp.model.GitHubRepositoryItem
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface APIInterface {

    // save scan code  api call
//    @POST("SaveQRCodeDetailsBulk")
//    fun syncScannedCodes(@Body scanRequest: ScanRequest): Call<ScanResponse>

    @GET("/repositories?")
    fun getGithubRepoList(@Query("since") since: String): Deferred<Response<List<GitHubRepositoryItem>>>

}