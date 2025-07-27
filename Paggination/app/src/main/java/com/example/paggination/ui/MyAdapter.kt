package com.example.paggination.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paggination.R

data class MyDataItem(val id: Int, var pageNo:Int)

class MyDiffCallback : DiffUtil.ItemCallback<MyDataItem>() {
    override fun areItemsTheSame(oldItem: MyDataItem, newItem: MyDataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyDataItem, newItem: MyDataItem): Boolean {
        return oldItem == newItem
    }
}

class MyAdapter : PagingDataAdapter<MyDataItem, MyAdapter.MyViewHolder>(MyDiffCallback()) {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MyDataItem?) {
            itemView.findViewById<TextView>(R.id.item_no).text= item?.id.toString()
            itemView.findViewById<TextView>(R.id.page_no).text = item?.pageNo.toString()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(view)
    }
}
