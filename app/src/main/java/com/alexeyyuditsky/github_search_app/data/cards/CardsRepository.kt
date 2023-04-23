package com.alexeyyuditsky.github_search_app.data.cards

import com.alexeyyuditsky.github_search_app.data.cards.cloud.ReposCloudDataSource
import com.alexeyyuditsky.github_search_app.data.cards.cloud.UsersCloudDataSource

interface CardsRepository {

    suspend fun fetchResult(query: String): CardsData

    class Base(
        private val usersCloudDataSource: UsersCloudDataSource,
        private val reposCloudDataSource: ReposCloudDataSource,
    ) : CardsRepository {

        override suspend fun fetchResult(query: String): CardsData {
            return try {
                val users = usersCloudDataSource.fetchUsers(query).toUserData()
                val repos = reposCloudDataSource.fetchRepos(query).toRepoData()
                if (users.isEmpty() && repos.isEmpty()) {
                    CardsData.Empty
                } else {
                    CardsData.Success(users, repos)
                }
            } catch (e: Exception) {
                CardsData.Fail(e)
            }
        }

    }

}