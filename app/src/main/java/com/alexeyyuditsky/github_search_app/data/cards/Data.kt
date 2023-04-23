package com.alexeyyuditsky.github_search_app.data.cards

sealed class Data {

    data class UserData(
        val userAvatarUrl: String,
        val userLogin: String,
        val userScore: Double,
    ) : Data()

    data class RepoData(
        val repoName: String,
        val repoForksCount: Long,
        val repoDescription: String,
    ) : Data()

}

