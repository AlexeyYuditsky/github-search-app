package com.alexeyyuditsky.github_search_app.domain.cards

import com.alexeyyuditsky.github_search_app.core.Abstract
import com.alexeyyuditsky.github_search_app.core.ErrorType
import com.alexeyyuditsky.github_search_app.core.ResourceProvider
import com.alexeyyuditsky.github_search_app.presentation.cards.CardUi
import com.alexeyyuditsky.github_search_app.presentation.cards.CardsUi

sealed class CardsDomain : Abstract.DomainToUi<CardsUi> {

    data class Success(
        private val cards: List<CardDomain>,
    ) : CardsDomain() {
        override fun map(resourceProvider: ResourceProvider): CardsUi {
            val cardsUi = toUi(cards)
            return CardsUi(cardsUi)
        }

        private fun toUi(list: List<CardDomain>): List<CardUi> {
            return list.map {
                when (it) {
                    is CardDomain.User -> CardUi.User(it.avatarUrl, it.login, it.score, it.htmlUrl)
                    is CardDomain.Repo -> CardUi.Repo(it.userLogin, it.name, it.forksCount, it.description)
                }
            }
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