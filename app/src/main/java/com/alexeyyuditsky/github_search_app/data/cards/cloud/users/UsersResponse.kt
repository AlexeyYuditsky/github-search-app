package com.alexeyyuditsky.github_search_app.data.cards.cloud.users

import com.alexeyyuditsky.github_search_app.data.cards.Data

data class UsersResponse(
    val items: List<UserCloud>,
) {

    fun toUserData(): List<Data> {
        return items.map {
            Data.UserData(
                it.avatarUrl,
                it.login,
                it.score
            )
        }
    }

}