package com.alexeyyuditsky.github_search_app.sl.cards

import com.alexeyyuditsky.github_search_app.presentation.cards.fragment.CardsViewModel
import com.alexeyyuditsky.github_search_app.sl.core.BaseFactory

class CardsFactory(
    cardsModule: CardsModule,
) : BaseFactory<CardsViewModel>(cardsModule)