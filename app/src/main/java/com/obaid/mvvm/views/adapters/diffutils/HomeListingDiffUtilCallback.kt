package com.obaid.mvvm.views.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.obaid.mvvm.data.models.responses.Hit

class HomeListingDiffUtilCallback(
    private val oldList: List<Hit>,
    private val newList: List<Hit>

) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldList[p0].id == newList[p1].id
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        // right now i am just assuming the data on ID, we can compare here what ever we want to!
        return oldList[p0].id == newList[p1].id
    }
}