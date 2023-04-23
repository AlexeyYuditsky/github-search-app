package com.alexeyyuditsky.github_search_app.data.cards.cloud

import com.alexeyyuditsky.github_search_app.data.cards.cloud.repos.ReposResponse
import com.alexeyyuditsky.github_search_app.data.cards.cloud.users.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CardsService {

    @GET("search/users")
    suspend fun fetchUsers(
        @Query("q") query: String,
    ): UsersResponse

    @GET("search/repositories")
    suspend fun fetchRepos(
        @Query("q") query: String,
    ): ReposResponse

}