package com.example.testgithubapp.network.repository

import com.example.testgithubapp.model.GitHubRepositoryItem
import com.example.testgithubapp.network.APIInterface


class ApiRepository(private val apiInterface: APIInterface) : BaseRepository() {

    suspend fun getDataList(since: String): List<GitHubRepositoryItem>? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getGithubRepoList(since).await() },
            error = "Error to retrieve Github trending repo list"
            //convert to mutable list
        )
    }

}
