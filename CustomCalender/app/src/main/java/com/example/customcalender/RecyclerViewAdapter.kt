package com.example.customcalender

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class NewDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }


}


class RecyclerViewAdapter(var kFunction1: (String) -> Unit) :
    ListAdapter<String, RecyclerViewAdapter.RecyclerViewHolder>(NewDiffUtil()) {

    var selectedDate = -1
    private var initial = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return RecyclerViewHolder(view)
    }


    fun setActive(position: Int, item: TextView, recyclerData: String) {
        selectedDate = position
        item.setBackgroundResource(R.drawable.yellow_circle)
        kFunction1(recyclerData)
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val recyclerData: String = currentList[position]
        holder.item.text = recyclerData
        if (initial && selectedDate == position) {
            holder.item.setBackgroundResource(R.drawable.yellow_circle)
            setActive(position, holder.item, recyclerData)
            initial = false
        } else {
            holder.item.setBackgroundColor(Color.argb(0, 0, 0, 0))

        }
        if (recyclerData != " ") {
            holder.item.setOnClickListener {
                if (selectedDate != position) {
                    notifyItemChanged(selectedDate)
                }
                selectedDate = position
                setActive(position, holder.item, recyclerData)
            }
        }

    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item = itemView.findViewById<TextView>(R.id.weekitem)
    }
}
