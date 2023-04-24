package com.alexeyyuditsky.github_search_app.domain.content

import com.alexeyyuditsky.github_search_app.core.Abstract
import com.alexeyyuditsky.github_search_app.core.ErrorType
import com.alexeyyuditsky.github_search_app.core.ResourceProvider
import com.alexeyyuditsky.github_search_app.presentation.content.data.ContentUi
import com.alexeyyuditsky.github_search_app.presentation.content.data.ContentsUi

sealed class ContentsDomain : Abstract.DomainToUi<ContentsUi> {

    data class Success(
        private val cards: List<ContentDomain>,
    ) : ContentsDomain() {

        override fun map(resourceProvider: ResourceProvider): ContentsUi {
            val contentsUi = toUi(cards)
            return ContentsUi(contentsUi)
        }

        private fun toUi(list: List<ContentDomain>): List<ContentUi> {
            return list.map {
                when (it) {
                    is ContentDomain.Dir -> ContentUi.Dir(it.name, it.type, it.folderUrl, it.fileUrl)
                    is ContentDomain.File -> ContentUi.File(it.name, it.type, it.fileUrl)
                }
            }
        }

    }

    data class Fail(
        private val e: ErrorType,
    ) : ContentsDomain() {

        override fun map(resourceProvider: ResourceProvider): ContentsUi {
            val errorMessage = errorMessage(e, resourceProvider)
            return ContentsUi(listOf(ContentUi.Fail(errorMessage)))
        }

    }

    object Empty : ContentsDomain() {
        override fun map(resourceProvider: ResourceProvider): ContentsUi {
            return ContentsUi(listOf(ContentUi.Empty))
        }
    }

}