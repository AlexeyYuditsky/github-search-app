package com.alexeyyuditsky.github_search_app.presentation.content

import com.alexeyyuditsky.github_search_app.presentation.TextMapper

sealed class ContentUi {

    open fun map(mapper: TextMapper) = Unit
    open fun same(item: ContentUi): Boolean = false
    open fun sameContent(item: ContentUi): Boolean = false

    abstract class Info(
        open val name: String,
    ) : ContentUi() {
        override fun same(item: ContentUi): Boolean {
            return when (item) {
                is Dir -> this.name == item.name
                is File -> this.name == item.name
                else -> false
            }
        }

        override fun sameContent(item: ContentUi): Boolean {
            return when (item) {
                is Dir -> this == item
                is File -> this == item
                else -> false
            }
        }
    }

    data class Dir(
        override val name: String,
        val type: String,
        val folderUrl: String,
        val fileUrl: String?,
    ) : Info(name) {
        override fun map(mapper: TextMapper) {
            mapper.map(name, folderUrl)
        }
    }

    data class File(
        override val name: String,
        val type: String,
        val fileUrl: String?,
    ) : Info(name) {
        override fun map(mapper: TextMapper) {
            mapper.map(name, fileUrl!!)
        }
    }

    data class Fail(
        private val message: String,
    ) : ContentUi() {
        override fun map(mapper: TextMapper) {
            mapper.map(message)
        }
    }

    object Progress : ContentUi()

    object Empty : ContentUi()

}