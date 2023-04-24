package com.alexeyyuditsky.github_search_app.domain.content

import com.alexeyyuditsky.github_search_app.data.content.ContentRepository

interface ContentInteractor {

    suspend fun fetchContent(login: String, repo: String, path: String = ""): ContentsDomain

    class Base(
        private val repository: ContentRepository,
    ) : ContentInteractor {

        override suspend fun fetchContent(login: String, repo: String, path: String): ContentsDomain {
            val contentData = repository.fetchContent(login, repo, path)
            return contentData.map()
        }

    }

}
