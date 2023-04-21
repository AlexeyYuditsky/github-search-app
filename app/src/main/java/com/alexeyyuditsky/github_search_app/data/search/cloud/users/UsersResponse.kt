package com.alexeyyuditsky.github_search_app.data.search.cloud.users

import com.alexeyyuditsky.github_search_app.data.search.UserData

data class UsersResponse(
    val items: List<UserCloud>,
) {

    fun toUserData(): List<UserData> {
        return items.map {
            UserData(
                it.avatarUrl,
                it.login,
                it.score
            )
        }
    }

}