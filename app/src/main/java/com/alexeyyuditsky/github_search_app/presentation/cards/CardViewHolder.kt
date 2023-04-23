package com.alexeyyuditsky.github_search_app.presentation.cards

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.databinding.ItemFailBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemRepoBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemUserBinding
import com.bumptech.glide.Glide

abstract class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: CardUi) = Unit

    class User(
        private val binding: ItemUserBinding,
    ) : CardViewHolder(binding.root) {

        override fun bind(item: CardUi) {
            item.map(object : TextMapper {
                override fun map(vararg values: String) {
                    Glide.with(itemView).load(values[0]).into(binding.image)
                    binding.name.text = values[1]
                    binding.score.text = values[2]
                }
            })
        }

    }

    class Repo(
        private val binding: ItemRepoBinding,
    ) : CardViewHolder(binding.root) {

        override fun bind(item: CardUi) {
            item.map(object : TextMapper {
                override fun map(vararg values: String) {
                    binding.name.text = values[0]
                    binding.forks.text = itemView.context.getString(R.string.forks, values[1])
                    binding.description.text = values[2]
                }
            })
        }

    }

    class Fail(
        private val binding: ItemFailBinding,
        private val retry: RetryClickListener,
    ) : CardViewHolder(binding.root) {

        override fun bind(item: CardUi) {
            item.map(object : TextMapper {
                override fun map(vararg values: String) {
                    binding.message.text = values[0]
                    binding.tryAgain.setOnClickListener { retry.invoke() }
                }
            })
        }

    }

    class Progress(
        view: View,
    ) : CardViewHolder(view)

    class Empty(
        view: View,
    ) : CardViewHolder(view)

}