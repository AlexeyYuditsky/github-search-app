package com.alexeyyuditsky.github_search_app.presentation.content.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.github_search_app.R
import com.alexeyyuditsky.github_search_app.databinding.ItemDirBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemFailBinding
import com.alexeyyuditsky.github_search_app.databinding.ItemFileBinding
import com.alexeyyuditsky.github_search_app.presentation.content.data.ContentUi
import com.alexeyyuditsky.github_search_app.presentation.content.data.DiffUtilCallback

typealias RetryClickListener = () -> Unit
typealias DirClickListener = (path: String) -> Unit
typealias FileClickListener = (url: String) -> Unit

class ContentAdapter(
    private val dir: DirClickListener,
    private val file: FileClickListener,
    private val retry: RetryClickListener,
) : RecyclerView.Adapter<ContentViewHolder>() {

    private val content = mutableListOf<ContentUi>()

    fun update(newContent: List<ContentUi>) {
        val diffCallback = DiffUtilCallback(content, newContent)
        val result = DiffUtil.calculateDiff(diffCallback, false)
        content.clear()
        content.addAll(newContent)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (content[position]) {
            is ContentUi.Dir -> R.layout.item_dir
            is ContentUi.File -> R.layout.item_file
            is ContentUi.Fail -> R.layout.item_fail
            is ContentUi.Empty -> R.layout.item_empty
            else -> R.layout.item_progress
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return when (viewType) {
            R.layout.item_dir -> {
                val binding = ItemDirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ContentViewHolder.Dir(binding, dir)
            }

            R.layout.item_file -> {
                val binding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ContentViewHolder.File(binding, file)
            }

            R.layout.item_fail -> {
                val binding = ItemFailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ContentViewHolder.Fail(binding, retry)
            }

            R.layout.item_empty -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
                ContentViewHolder.Empty(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
                ContentViewHolder.Progress(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(content[position])
    }

    override fun getItemCount(): Int = content.size

}