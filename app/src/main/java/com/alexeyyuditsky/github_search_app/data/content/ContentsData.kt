package com.alexeyyuditsky.github_search_app.data.content

import com.alexeyyuditsky.github_search_app.core.Abstract
import com.alexeyyuditsky.github_search_app.data.content.cloud.ContentCloud
import com.alexeyyuditsky.github_search_app.domain.content.ContentDomain
import com.alexeyyuditsky.github_search_app.domain.content.ContentsDomain

sealed class ContentsData : Abstract.DataToDomain<ContentsDomain> {

    data class Success(
        private val content: List<ContentCloud>,
    ) : ContentsData() {

        override fun map(): ContentsDomain {
            val contentDomain = toDomain(content)
            return ContentsDomain.Success(contentDomain)
        }

        private fun toDomain(list: List<ContentCloud>): List<ContentDomain> {
            return list.map {
                if (it.type == "dir") ContentDomain.Dir(it.name, it.type, it.folderUrl, it.fileUrl)
                else ContentDomain.File(it.name, it.type, it.fileUrl)
            }
        }

    }

    data class Fail(
        private val e: Exception,
    ) : ContentsData() {
        override fun map(): ContentsDomain {
            return ContentsDomain.Fail(errorType(e))
        }
    }

    object Empty : ContentsData() {
        override fun map(): ContentsDomain {
            return ContentsDomain.Empty
        }
    }

}