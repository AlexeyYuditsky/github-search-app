package com.alexeyyuditsky.github_search_app.presentation.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.github_search_app.core.ResourceProvider
import com.alexeyyuditsky.github_search_app.domain.cards.CardsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardsViewModel(
    private val interactor: CardsInteractor,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _cardsLiveData = MutableLiveData<List<CardUi>>()
    val cardsLiveData: LiveData<List<CardUi>> get() = _cardsLiveData

    init {
        fetchCards("kotlin repo")
    }

    fun fetchCards(query: String) = viewModelScope.launch {
        _cardsLiveData.value = listOf(CardUi.Progress)
        withContext(Dispatchers.IO) {
            val cardsDomain = interactor.fetchCards(query)
            val cardsUi = cardsDomain.map(resourceProvider)
            withContext(Dispatchers.Main) {
                _cardsLiveData.value = cardsUi.map()
            }
        }
    }

}