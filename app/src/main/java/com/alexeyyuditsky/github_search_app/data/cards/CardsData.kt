package com.alexeyyuditsky.github_search_app.data.cards

import com.alexeyyuditsky.github_search_app.core.Abstract
import com.alexeyyuditsky.github_search_app.domain.cards.CardDomain
import com.alexeyyuditsky.github_search_app.domain.cards.CardsDomain

sealed class CardsData : Abstract.DataToDomain<CardsDomain> {

    data class Success(
        private val users: List<Data>,
        private val repos: List<Data>,
    ) : CardsData() {

        override fun map(): CardsDomain {
            val sortedList = sort(users, repos)
            val cardsDomain = toDomain(sortedList)
            return CardsDomain.Success(cardsDomain)
        }

        private fun sort(users: List<Data>, repos: List<Data>): List<Data> {
            return (users + repos).sortedBy {
                when (it) {
                    is Data.UserData -> it.userLogin
                    is Data.RepoData -> it.repoName
                }
            }
        }

        private fun toDomain(cardsList: List<Data>): List<CardDomain> {
            return cardsList.map {
                when (it) {
                    is Data.UserData -> CardDomain.User(it.userAvatarUrl, it.userLogin, it.userScore)
                    is Data.RepoData -> CardDomain.Repo(it.repoName, it.repoForksCount, it.repoDescription)
                }
            }
        }

    }

    data class Fail(
        private val e: Exception,
    ) : CardsData() {
        override fun map(): CardsDomain {
            return CardsDomain.Fail(errorType(e))
        }
    }

    object Empty : CardsData() {
        override fun map(): CardsDomain {
            return CardsDomain.Empty
        }
    }

}