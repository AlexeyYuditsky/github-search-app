package com.alexeyyuditsky.github_search_app.sl.core

import androidx.lifecycle.ViewModel

interface BaseModule<T : ViewModel> {
    fun getViewModel(): T
}