package com.alexeyyuditsky.github_search_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.core.Const.pathToFile
import com.alexeyyuditsky.github_search_app.presentation.cards.fragment.CardsFragment
import com.alexeyyuditsky.github_search_app.presentation.content.fragment.ContentFragment
import com.alexeyyuditsky.github_search_app.presentation.content.fragment.WebViewFragment

class MainActivity : AppCompatActivity(R.layout.activity_main), FragmentRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragmentContainer, CardsFragment())
            }
        }
    }

    override fun showWebView(url: String) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, WebViewFragment.newInstance(url))
            addToBackStack(null)
        }
    }

    override fun showContent(login: String, repo: String, path: String) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, ContentFragment.newInstance(login, repo, path))
            addToBackStack(null)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        pathToFile = pathToFile.substringBeforeLast('/')
    }

}