package com.example.testgithubapp.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class GitHubRepository(
    val gitHubRepositories : List<GitHubRepositoryItem>
)

@JsonClass(generateAdapter = true)
data class GitHubRepositoryItem(
    @Json(name = "builtBy")
    val builtBy: List<BuiltBy>?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "forks")
    val forks: Int?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "languageColor")
    val languageColor: String?,
    @Json(name = "rank")
    val rank: Int?,
    @Json(name = "repositoryName")
    val repositoryName: String?,
    @Json(name = "since")
    val since: String?,
    @Json(name = "starsSince")
    val starsSince: Int?,
    @Json(name = "totalStars")
    val totalStars: Int?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "username")
    val username: String?,
    var checked: Boolean = false
)

@JsonClass(generateAdapter = true)
data class BuiltBy(
    @Json(name = "avatar")
    val avatar: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "username")
    val username: String?
)