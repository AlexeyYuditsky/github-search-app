package com.alexeyyuditsky.github_search_app.data.content.cloud

interface ContentCloudDataSource {

    suspend fun fetchContent(login: String, repo: String, path: String = ""): List<ContentCloud>

    class Base(
        private val service: ContentService,
    ) : ContentCloudDataSource {

        override suspend fun fetchContent(login: String, repo: String, path: String): List<ContentCloud> {
            return service.fetchContent(login, repo, path)
        }

    }

}