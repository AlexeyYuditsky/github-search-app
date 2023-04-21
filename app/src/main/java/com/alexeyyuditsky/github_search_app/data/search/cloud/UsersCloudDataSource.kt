package com.alexeyyuditsky.github_search_app.data.search.cloud

import com.alexeyyuditsky.github_search_app.data.search.cloud.users.UsersResponse

interface UsersCloudDataSource {

    suspend fun fetchUsers(query: String): UsersResponse

    class Base(
        private val service: SearchService,
    ) : UsersCloudDataSource {

        override suspend fun fetchUsers(query: String): UsersResponse {
            return service.fetchUsers(query)
        }

    }

}

