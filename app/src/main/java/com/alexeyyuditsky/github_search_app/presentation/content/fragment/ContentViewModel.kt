package com.alexeyyuditsky.github_search_app.presentation.content.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.github_search_app.core.ResourceProvider
import com.alexeyyuditsky.github_search_app.domain.content.ContentInteractor
import com.alexeyyuditsky.github_search_app.presentation.content.data.ContentUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContentViewModel(
    private val interactor: ContentInteractor,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _contentLiveData = MutableLiveData<List<ContentUi>>()
    val contentLiveData: LiveData<List<ContentUi>> get() = _contentLiveData

    fun fetchContent(login: String, repo: String, path: String = "") = viewModelScope.launch {
        _contentLiveData.value = listOf(ContentUi.Progress)
        withContext(Dispatchers.IO) {
            val contentDomain = interactor.fetchContent(login, repo, path)
            val contentsUi = contentDomain.map(resourceProvider)
            withContext(Dispatchers.Main) {
                _contentLiveData.value = contentsUi.map()
            }
        }
    }

}