package com.example.flowandroid.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class NewDiff : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return true
    }


}

class RecyclerViewAdapter : ListAdapter<Int, RecyclerViewAdapter.ViewHolder>(NewDiff()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<TextView>(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.text = currentList[position].toString()
    }
}