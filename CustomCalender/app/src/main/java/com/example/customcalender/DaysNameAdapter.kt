package com.example.customcalender

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.customcalender.databinding.EachItemBinding

class DisplayDaysName(private val data: List<String>) :
    RecyclerView.Adapter<DisplayDaysName.StringViewHolder>() {

    class StringViewHolder(val binding: EachItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val binding = DataBindingUtil.inflate<EachItemBinding>(
            LayoutInflater.from(parent.context), R.layout.each_item, parent, false
        )
        return StringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.binding.weekitem.text = data[position]

    }

    override fun getItemCount(): Int {
        return data.size
    }
}


