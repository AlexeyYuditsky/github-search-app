package com.alexeyyuditsky.github_search_app.data.cards.cloud.users

import com.squareup.moshi.Json

data class UserCloud(
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
    val login: String,
    val score: Double,
)

