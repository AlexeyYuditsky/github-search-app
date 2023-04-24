package com.alexeyyuditsky.github_search_app.presentation

interface FragmentRouter {

    fun showContent(login: String, repo: String, path: String = "")

    fun showWebView(url:String)

}