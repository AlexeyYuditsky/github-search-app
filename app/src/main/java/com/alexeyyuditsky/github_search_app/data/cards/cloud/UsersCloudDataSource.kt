package com.alexeyyuditsky.github_search_app.data.cards.cloud

import com.alexeyyuditsky.github_search_app.data.cards.cloud.users.UsersResponse

interface UsersCloudDataSource {

    suspend fun fetchUsers(query: String): UsersResponse

    class Base(
        private val service: CardsService,
    ) : UsersCloudDataSource {

        override suspend fun fetchUsers(query: String): UsersResponse {
            return service.fetchUsers(query)
        }

    }

}

