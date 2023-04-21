package com.alexeyyuditsky.github_search_app.data.search

data class RepoData(
    val repoName: String,
    val repoForksCount: Long,
    val repoDescription: String,
)