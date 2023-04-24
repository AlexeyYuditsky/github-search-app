package com.alexeyyuditsky.github_search_app.data.cards.cloud.repos

import com.alexeyyuditsky.github_search_app.data.cards.Data

data class ReposResponse(
    val items: List<RepoCloud>,
) {

    fun toRepoData(): List<Data> {
        return items.map {
            Data.RepoData(
                it.login.substringBefore('/'),
                it.name,
                it.forksCount,
                it.description ?: ""
            )
        }
    }

}