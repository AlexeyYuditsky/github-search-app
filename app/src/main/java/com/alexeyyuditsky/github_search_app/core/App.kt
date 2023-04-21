package com.alexeyyuditsky.github_search_app.core

import android.app.Application
import com.alexeyyuditsky.github_search_app.sl.core.CoreModule

class App : Application() {

    private val coreModule = CoreModule

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    /*fun reposFactory(): ReposFactory {
        return ReposFactory(ReposModule(coreModule))
    }

    fun issuesFactory(): IssuesFactory {
        return IssuesFactory(IssuesModule(coreModule))
    }*/

}