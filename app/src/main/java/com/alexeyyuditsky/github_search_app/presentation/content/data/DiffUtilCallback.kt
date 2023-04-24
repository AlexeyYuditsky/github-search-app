package com.alexeyyuditsky.github_search_app.presentation.content.data

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldList: List<ContentUi>,
    private val newList: List<ContentUi>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].same(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].sameContent(newList[newItemPosition])
    }

}