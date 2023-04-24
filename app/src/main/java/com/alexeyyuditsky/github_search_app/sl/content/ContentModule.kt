package com.alexeyyuditsky.github_search_app.sl.content

import com.alexeyyuditsky.github_search_app.data.content.ContentRepository
import com.alexeyyuditsky.github_search_app.data.content.cloud.ContentCloudDataSource
import com.alexeyyuditsky.github_search_app.data.content.cloud.ContentService
import com.alexeyyuditsky.github_search_app.domain.content.ContentInteractor
import com.alexeyyuditsky.github_search_app.presentation.content.ContentViewModel
import com.alexeyyuditsky.github_search_app.sl.core.BaseModule
import com.alexeyyuditsky.github_search_app.sl.core.CoreModule

class ContentModule(
    private val coreModule: CoreModule,
) : BaseModule<ContentViewModel> {

    override fun getViewModel(): ContentViewModel {
        return ContentViewModel(fetchContentInteractor(), coreModule.resourceProvider)
    }

    private fun fetchContentInteractor(): ContentInteractor {
        return ContentInteractor.Base(fetchContentRepository())
    }

    private fun fetchContentRepository(): ContentRepository {
        return ContentRepository.Base(fetchContentCloudDataSource())
    }

    private fun fetchContentCloudDataSource(): ContentCloudDataSource {
        return ContentCloudDataSource.Base(fetchService())
    }

    private fun fetchService(): ContentService {
        return coreModule.makeService(ContentService::class.java)
    }

}