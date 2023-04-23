package com.alexeyyuditsky.github_search_app.presentation.cards

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldList: List<CardUi>,
    private val newList: List<CardUi>,
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