package com.alexeyyuditsky.github_search_app.data.search

import com.alexeyyuditsky.github_search_app.core.Abstract
import com.alexeyyuditsky.github_search_app.core.ErrorType
import com.alexeyyuditsky.github_search_app.core.ResourceProvider

sealed class CardsData : Abstract.DataToDomain<CardsDomain> {

    data class Success(
        private val users: List<UserData>,
        private val repos: List<RepoData>,
    ) : CardsData() {
        override fun map(): CardsDomain {

            // превращаем users и repos в cardDomain.user и cardDomain.repo
            // сортируем user.login и repo.name

            val logins = users.map { it.userLogin }
            val names = repos.map { it.repoName }
            val tempList = ArrayList(logins)
            tempList.addAll(names)




            return CardsDomain.Success(listOf<CardDomain>())
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

sealed class CardsDomain : Abstract.DomainToUi<CardsUi> {

    data class Success(
        private val cards: List<CardDomain>,
    ) : CardsDomain() {
        override fun map(resourceProvider: ResourceProvider): CardsUi {
            // cards.map() -> CardUi
            return CardsUi(listOf<CardUi.Repo>())
        }
    }

    data class Fail(
        private val e: ErrorType,
    ) : CardsDomain() {
        override fun map(resourceProvider: ResourceProvider): CardsUi {
            val errorMessage = errorMessage(e, resourceProvider)
            return CardsUi(listOf(CardUi.Fail(errorMessage)))
        }
    }

    object Empty : CardsDomain() {
        override fun map(resourceProvider: ResourceProvider): CardsUi {
            return CardsUi(listOf(CardUi.Empty))
        }
    }

}

sealed class CardDomain {
    class User(val avatarUrl: String, val login: String, val score: Double)
    class Repo(val name: String, val forksCount: Long, val description: String)
}

data class CardsUi(
    val cards: List<CardUi>,
)

sealed class CardUi {
    class User(val avatarUrl: String, val login: String, val score: Double) : CardUi()
    class Repo(val name: String, val forksCount: Long, val description: String) : CardUi()
    class Fail(message: String) : CardUi()
    object Empty : CardUi()
}
