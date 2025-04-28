package com.example.nestedrecyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MainAdapter(private var matrix: ArrayList<ArrayList<Int>>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_rview = itemView.findViewById<RecyclerView>(R.id.item_rview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutinflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_final, parent, false)
        return ViewHolder(layoutinflater)
    }

    override fun getItemCount(): Int {
        return matrix.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item_rview.adapter = itemsAdapter(matrix[position])
    }
}


class itemsAdapter(var array: ArrayList<Int>) :
    RecyclerView.Adapter<itemsAdapter.ViewHolderitems>() {
    class ViewHolderitems(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Imagebox = itemView.findViewById<ImageView>(R.id.Imagebox)
        var textbox=itemView.findViewById<TextView>(R.id.textBox)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): itemsAdapter.ViewHolderitems {
        var layoutinflater =
            LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolderitems(layoutinflater)
    }


    private fun getImage(p: Int): Int {

        return when (p) {
            0 -> R.drawable.img_0
            1 -> R.drawable.img_1
            2 -> R.drawable.img_2
            3 -> R.drawable.img_3
            4 -> R.drawable.img_4
            else -> R.drawable.img_4
        }

    }

    override fun onBindViewHolder(holder: ViewHolderitems, position: Int) {

        holder.Imagebox.setImageResource(getImage(array[position]))//setText(().toString())
        holder.textbox.setText("image ${array[position]}")
        if (position % 2 == 0)
            holder.Imagebox.setBackgroundColor(Color.parseColor("#00B4DB"))
        else
            holder.Imagebox.setBackgroundColor(Color.parseColor("#0083B0"))
    }


    override fun getItemCount(): Int {
        return array.size
    }

}