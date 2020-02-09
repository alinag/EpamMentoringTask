package ru.test.daggerkotlin.adapters.itemtouchhelper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class ItemTouchHelperCallback @Inject constructor(var adapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {

//    @Inject
//    private lateinit var adapter: ItemTouchHelperAdapter

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(
            dragFlags,
            swipeFlags
        )
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDissmiss(viewHolder.adapterPosition)
    }

    override fun isItemViewSwipeEnabled() = true

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView!!, viewHolder)
        viewHolder.itemView.alpha = 1f
        if (viewHolder is ItemTouchHelperViewHolder) { // Tell the view holder it's time to restore the idle state
            val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
            itemViewHolder.onItemClear()
        }
    }
}