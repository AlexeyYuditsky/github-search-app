package com.alexeyyuditsky.github_search_app.core

import android.app.Application
import com.alexeyyuditsky.github_search_app.sl.cards.CardsFactory
import com.alexeyyuditsky.github_search_app.sl.cards.CardsModule
import com.alexeyyuditsky.github_search_app.sl.content.ContentFactory
import com.alexeyyuditsky.github_search_app.sl.content.ContentModule
import com.alexeyyuditsky.github_search_app.sl.core.CoreModule

class App : Application() {

    private val coreModule = CoreModule

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun cardsFactory(): CardsFactory {
        return CardsFactory(CardsModule(coreModule))
    }

    fun contentFactory(): ContentFactory {
        return ContentFactory(ContentModule(coreModule))
    }

}