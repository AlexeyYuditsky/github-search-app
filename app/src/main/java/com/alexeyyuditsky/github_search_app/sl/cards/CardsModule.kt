package com.alexeyyuditsky.github_search_app.sl.cards

import com.alexeyyuditsky.github_search_app.data.cards.CardsRepository
import com.alexeyyuditsky.github_search_app.data.cards.cloud.CardsService
import com.alexeyyuditsky.github_search_app.data.cards.cloud.ReposCloudDataSource
import com.alexeyyuditsky.github_search_app.data.cards.cloud.UsersCloudDataSource
import com.alexeyyuditsky.github_search_app.domain.cards.CardsInteractor
import com.alexeyyuditsky.github_search_app.presentation.cards.fragment.CardsViewModel
import com.alexeyyuditsky.github_search_app.sl.core.BaseModule
import com.alexeyyuditsky.github_search_app.sl.core.CoreModule

class CardsModule(
    private val coreModule: CoreModule,
) : BaseModule<CardsViewModel> {

    override fun getViewModel(): CardsViewModel {
        return CardsViewModel(fetchCardsInteractor(), coreModule.resourceProvider)
    }

    private fun fetchCardsInteractor(): CardsInteractor {
        return CardsInteractor.Base(fetchCardsRepository())
    }

    private fun fetchCardsRepository(): CardsRepository {
        return CardsRepository.Base(fetchUsersCloudDataSource(), fetchReposCloudDataSource())
    }

    private fun fetchUsersCloudDataSource(): UsersCloudDataSource {
        return UsersCloudDataSource.Base(fetchService())
    }

    private fun fetchReposCloudDataSource(): ReposCloudDataSource {
        return ReposCloudDataSource.Base(fetchService())
    }

    private fun fetchService(): CardsService {
        return coreModule.makeService(CardsService::class.java)
    }

}