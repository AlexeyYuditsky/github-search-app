package com.alexeyyuditsky.github_search_app.data.search.cloud

import com.alexeyyuditsky.github_search_app.data.search.cloud.repos.ReposResponse
import com.alexeyyuditsky.github_search_app.data.search.cloud.users.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

    @GET("search/users")
    suspend fun fetchUsers(
        @Query("q") query: String,
    ): UsersResponse

    @GET("users/{user}/repos")
    suspend fun fetchRepos(
        @Path("user") query: String,
    ): List<ReposResponse>

}