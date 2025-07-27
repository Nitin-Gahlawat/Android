package com.example.callmethodapihit.ui.displayRview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.callmethodapihit.data.ResponseData
import com.example.callmethodapihit.R

class newDiff: DiffUtil.ItemCallback<ResponseData>() {
    override fun areItemsTheSame(oldItem: ResponseData, newItem: ResponseData): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: ResponseData, newItem: ResponseData): Boolean {
       return oldItem.id == newItem.id
    }


}


class RecyclerViewAdapter: ListAdapter<ResponseData, RecyclerViewAdapter.ViewHolder>(newDiff()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img=itemView.findViewById<ImageView>(R.id.item_img)
        var price=itemView.findViewById<TextView>(R.id.Price)
        var title=itemView.findViewById<TextView>(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_items,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=getItem(position)
        Glide.with(holder.img.context).load(item.image).into(holder.img)
        holder.title.text=item.title
        holder.price.text="Price:${item.price}"
    }
}