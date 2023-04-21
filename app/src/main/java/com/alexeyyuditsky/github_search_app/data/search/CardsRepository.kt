package com.alexeyyuditsky.github_search_app.data.search

import com.alexeyyuditsky.github_search_app.core.log
import com.alexeyyuditsky.github_search_app.data.search.cloud.ReposCloudDataSource
import com.alexeyyuditsky.github_search_app.data.search.cloud.UsersCloudDataSource
import com.alexeyyuditsky.github_search_app.data.search.cloud.repos.toRepoData

interface CardsRepository {

    suspend fun fetchResult(query: String)/*: CardsData*/

    class Base(
        private val usersCloudDataSource: UsersCloudDataSource,
        private val reposCloudDataSource: ReposCloudDataSource,
    ) : CardsRepository {

        override suspend fun fetchResult(query: String)/*: CardsData*/ {
            // return try {
            val users = usersCloudDataSource.fetchUsers(query).toUserData()
            val repos = reposCloudDataSource.fetchRepos(query).toRepoData()

            val logins = users.map { it.userLogin }
            val names = repos.map { it.repoName }
            val tempList = ArrayList(logins + names)
            tempList.addAll(names)
            tempList.sort()
            log(tempList)

            // CardsData.Success(users, repos)
            // } catch (e: Exception) {
            //  CardsData.Fail(e)
            //  }
        }

    }

}
