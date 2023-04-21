package com.alexeyyuditsky.github_search_app.data.search.cloud.repos

import com.alexeyyuditsky.github_search_app.data.search.RepoData

data class ReposResponse(
    val name: String,
    val owner: RepoCloud,
)

fun List<ReposResponse>.toRepoData(): List<RepoData> {
    return this.map {
        RepoData(
            it.name,
            it.owner.forksCount,
            it.owner.description ?: ""
        )
    }
}

