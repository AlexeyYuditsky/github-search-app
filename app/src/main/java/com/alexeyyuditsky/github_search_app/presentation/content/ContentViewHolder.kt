package com.alexeyyuditsky.github_search_app.presentation.content

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.github_search_app.databinding.ItemDirBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemFailBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemFileBinding
import com.alexeyyuditsky.github_search_app.presentation.TextMapper

abstract class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: ContentUi) = Unit

    class Dir(
        private val binding: ItemDirBinding,
        private val dir: DirClickListener,
    ) : ContentViewHolder(binding.root) {

        override fun bind(item: ContentUi) {
            item.map(object : TextMapper {
                override fun map(vararg values: String) {
                    binding.textView.text = values[0]
                    itemView.setOnClickListener {
                        dir.invoke(values[0])
                    }
                }
            })
        }

    }

    class File(
        private val binding: ItemFileBinding,
        private val file: FileClickListener,
    ) : ContentViewHolder(binding.root) {

        override fun bind(item: ContentUi) {
            item.map(object : TextMapper {
                override fun map(vararg values: String) {
                    binding.textView.text = values[0]
                    itemView.setOnClickListener {
                        file.invoke(values[1])
                    }
                }
            })
        }

    }

    class Fail(
        private val binding: ItemFailBinding,
        private val retry: RetryClickListener,
    ) : ContentViewHolder(binding.root) {

        override fun bind(item: ContentUi) {
            item.map(object : TextMapper {
                override fun map(vararg values: String) {
                    binding.message.text = values[0]
                    binding.tryAgain.setOnClickListener {
                        retry.invoke()
                    }
                }
            })
        }

    }

    class Empty(
        view: View,
    ) : ContentViewHolder(view)

    class Progress(
        view: View,
    ) : ContentViewHolder(view)

}