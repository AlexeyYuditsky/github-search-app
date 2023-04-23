package com.alexeyyuditsky.github_search_app.domain.cards

import com.alexeyyuditsky.github_search_app.data.cards.CardsRepository

interface CardsInteractor {

    suspend fun fetchCards(query: String): CardsDomain

    class Base(
        private val repository: CardsRepository,
    ) : CardsInteractor {

        override suspend fun fetchCards(query: String): CardsDomain {
            val cardsData = repository.fetchResult(query)
            return cardsData.map()
        }

    }

}
