package com.alexeyyuditsky.github_search_app.data.search.cloud.repos

import com.squareup.moshi.Json

data class RepoCloud(
    @field:Json(name = "forks_count")
    val forksCount: Long,
    val description: String?,
)