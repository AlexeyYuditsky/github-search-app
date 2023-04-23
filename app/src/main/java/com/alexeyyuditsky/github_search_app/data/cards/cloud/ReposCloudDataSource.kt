package com.alexeyyuditsky.github_search_app.data.cards.cloud

import com.alexeyyuditsky.github_search_app.data.cards.cloud.repos.ReposResponse

interface ReposCloudDataSource {

    suspend fun fetchRepos(query: String): ReposResponse

    class Base(
        private val service: CardsService,
    ) : ReposCloudDataSource {

        override suspend fun fetchRepos(query: String): ReposResponse {
            return service.fetchRepos(query)
        }

    }

}