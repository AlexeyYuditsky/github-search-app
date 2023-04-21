package com.alexeyyuditsky.github_search_app.data.search.cloud

import com.alexeyyuditsky.github_search_app.data.search.cloud.repos.ReposResponse

interface ReposCloudDataSource {

    suspend fun fetchRepos(query: String): List<ReposResponse>

    class Base(
        private val service: SearchService,
    ) : ReposCloudDataSource {

        override suspend fun fetchRepos(query: String): List<ReposResponse> {
            return service.fetchRepos(query)
        }

    }

}