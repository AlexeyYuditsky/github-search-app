package com.alexeyyuditsky.github_search_app.data.content.cloud

import com.squareup.moshi.Json

data class ContentCloud(
    val name: String,
    val type: String,
    @field:Json(name = "url")
    val folderUrl: String,
    @field:Json(name = "download_url")
    val fileUrl: String?,
)