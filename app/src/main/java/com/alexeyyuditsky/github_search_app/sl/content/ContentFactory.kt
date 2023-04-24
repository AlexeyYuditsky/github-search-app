package com.alexeyyuditsky.github_search_app.sl.content

import com.alexeyyuditsky.github_search_app.presentation.content.ContentViewModel
import com.alexeyyuditsky.github_search_app.sl.core.BaseFactory

class ContentFactory(
    contentModule: ContentModule,
) : BaseFactory<ContentViewModel>(contentModule)