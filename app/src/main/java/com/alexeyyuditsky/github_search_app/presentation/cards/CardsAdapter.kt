package com.alexeyyuditsky.github_search_app.presentation.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.databinding.ItemFailBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemRepoBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemUserBinding

typealias RetryClickListener = () -> Unit
typealias ContentClickListener = (login: String, repo: String, path: String) -> Unit

class CardsAdapter(
    private val retry: RetryClickListener,
    private val content: ContentClickListener,
) : RecyclerView.Adapter<CardViewHolder>() {

    private val cards = mutableListOf<CardUi>()

    fun update(newCards: List<CardUi>) {
        val diffCallback = DiffUtilCallback(cards, newCards)
        val result = DiffUtil.calculateDiff(diffCallback, false)
        cards.clear()
        cards.addAll(newCards)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (cards[position]) {
            is CardUi.User -> R.layout.item_user
            is CardUi.Repo -> R.layout.item_repo
            is CardUi.Fail -> R.layout.item_fail
            is CardUi.Empty -> R.layout.item_empty
            else -> R.layout.item_progress
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return when (viewType) {
            R.layout.item_user -> {
                val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CardViewHolder.User(binding)
            }

            R.layout.item_repo -> {
                val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CardViewHolder.Repo(binding, content)
            }

            R.layout.item_fail -> {
                val binding = ItemFailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CardViewHolder.Fail(binding, retry)
            }

            R.layout.item_empty -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
                CardViewHolder.Empty(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
                CardViewHolder.Progress(view)
            }
        }
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int = cards.size

}