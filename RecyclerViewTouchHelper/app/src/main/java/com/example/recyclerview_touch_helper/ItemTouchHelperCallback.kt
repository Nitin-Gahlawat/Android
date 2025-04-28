package com.example.recyclerview_touch_helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections


class ItemTouchHelperCallback(var adapter:DataAdapters,var dataList:ArrayList<GmailFormat>): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        Collections.swap(dataList, fromPosition, toPosition)
        adapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction==ItemTouchHelper.LEFT) {
            val position = viewHolder.adapterPosition
            dataList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }else if(direction == ItemTouchHelper.RIGHT){
            adapter.notifyItemChanged(viewHolder.adapterPosition)
        }
    }
}