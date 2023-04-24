package com.alexeyyuditsky.github_search_app.domain.content

sealed class ContentDomain {

    data class Dir(
        val name: String,
        val type: String,
        val folderUrl: String,
        val fileUrl: String?,
    ) : ContentDomain()

    data class File(
        val name: String,
        val type: String,
        val fileUrl: String?,
    ) : ContentDomain()

}

