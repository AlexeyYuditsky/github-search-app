package com.alexeyyuditsky.github_search_app.data.cards.cloud.repos

import com.squareup.moshi.Json

data class RepoCloud(
    val name: String,
    @field:Json(name = "forks_count")
    val forksCount: Long,
    val description: String?,
)