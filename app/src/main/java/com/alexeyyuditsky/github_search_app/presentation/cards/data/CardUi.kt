package com.alexeyyuditsky.github_search_app.presentation.cards.data

import com.alexeyyuditsky.github_search_app.presentation.TextMapper

sealed class CardUi {

    open fun map(mapper: TextMapper) = Unit
    open fun same(item: CardUi): Boolean = false
    open fun sameContent(item: CardUi): Boolean = false

    abstract class Info(
        open val title: String,
    ) : CardUi() {
        override fun same(item: CardUi): Boolean {
            return when (item) {
                is User -> this.title == item.title
                is Repo -> this.title == item.title
                else -> false
            }
        }

        override fun sameContent(item: CardUi): Boolean {
            return when (item) {
                is User -> this == item
                is Repo -> this == item
                else -> false
            }
        }
    }

    data class User(
        private val avatarUrl: String,
        override val title: String,
        private val score: Double,
        private val htmlUrl: String,
        ) : Info(title) {
        override fun map(mapper: TextMapper) {
            mapper.map(avatarUrl, title, score.toString(), htmlUrl)
        }
    }

    data class Repo(
        private val userLogin: String,
        override val title: String,
        private val forksCount: Long,
        private val description: String,
    ) : Info(title) {
        override fun map(mapper: TextMapper) {
            mapper.map(userLogin, title, forksCount.toString(), description)
        }
    }

    data class Fail(
        private val message: String,
    ) : CardUi() {
        override fun map(mapper: TextMapper) {
            mapper.map(message)
        }
    }

    object Progress : CardUi()

    object Empty : CardUi()

}