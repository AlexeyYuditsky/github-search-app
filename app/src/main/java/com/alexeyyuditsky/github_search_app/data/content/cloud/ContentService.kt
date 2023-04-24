package com.alexeyyuditsky.github_search_app.data.content.cloud

import retrofit2.http.GET
import retrofit2.http.Path

interface ContentService {

    @GET("repos/{login}/{repo}/contents{path}")
    suspend fun fetchContent(
        @Path("login") login: String,
        @Path("repo") repo: String,
        @Path("path") path: String = "",
    ): List<ContentCloud>

}

