package com.alexeyyuditsky.github_search_app.domain.cards

sealed class CardDomain {

    class User(
        val avatarUrl: String,
        val login: String,
        val score: Double,
    ) : CardDomain()

    class Repo(
        val name: String,
        val forksCount: Long,
        val description: String,
    ) : CardDomain()

}