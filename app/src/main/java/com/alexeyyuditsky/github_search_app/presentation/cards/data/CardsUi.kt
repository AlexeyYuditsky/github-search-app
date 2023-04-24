package com.alexeyyuditsky.github_search_app.presentation.cards.data

data class CardsUi(
    val cards: List<CardUi>,
) {

    fun map(): List<CardUi> {
        return cards
    }

}