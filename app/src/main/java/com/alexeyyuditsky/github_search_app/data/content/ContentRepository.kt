package com.alexeyyuditsky.github_search_app.data.content

import com.alexeyyuditsky.github_search_app.core.log
import com.alexeyyuditsky.github_search_app.data.content.cloud.ContentCloudDataSource

interface ContentRepository {

    suspend fun fetchContent(login: String, repo: String, path: String = ""): ContentsData

    class Base(
        private val cloudDataSource: ContentCloudDataSource,
    ) : ContentRepository {

        override suspend fun fetchContent(login: String, repo: String, path: String): ContentsData {
            return try {
                val content = cloudDataSource.fetchContent(login, repo, path)
                if (content.isEmpty()) {
                    ContentsData.Empty
                } else {
                    ContentsData.Success(content)
                }
            } catch (e: Exception) {
                log("exception = ${e.message}")
                ContentsData.Fail(e)
            }
        }

    }

}