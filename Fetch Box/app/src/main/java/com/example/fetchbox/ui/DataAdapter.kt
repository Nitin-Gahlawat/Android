package com.example.fetchbox.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fetchbox.R
import com.example.fetchbox.data.Product

class NewDiff : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title == newItem.title
    }

}


class DataAdapter : ListAdapter<Product, DataAdapter.ViewHolder>(NewDiff()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.textView)
        var img = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        var li = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return ViewHolder(li)
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        Log.d("items", "$position ${getItem(position).title}")
        holder.title.text = getItem(position).title
        Glide.with(holder.img.context).load(getItem(position).thumbnail).into(holder.img);
        holder.img.layoutParams.height = 200
    }


}