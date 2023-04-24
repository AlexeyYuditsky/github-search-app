package com.alexeyyuditsky.github_search_app.presentation.content.data

data class ContentsUi(
    val content: List<ContentUi>,
) {

    fun map(): List<ContentUi> {
        return content
    }

}