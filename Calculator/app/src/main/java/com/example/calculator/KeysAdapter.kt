package com.example.calculator

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var keys: MaterialButton =
        itemView.findViewById(R.id.KeyButton)
}

class KeysAdapter : ListAdapter<String, StringViewHolder>(StringDiffCallback()) {

    private var result = ""

    lateinit var updateTextView: (String) -> (Unit)

    lateinit var showToast: (String) -> (Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.keys_element, parent, false) as View
        return StringViewHolder(textView)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        val item: String? = getItem(position)
        holder.keys.text = item
        if (holder.keys.text == "=") {
            holder.keys.setBackgroundColor(Color.parseColor("#FF5722"))
        }
        holder.keys.setOnClickListener {
            when (holder.keys.text) {
                "=" -> {
                    if (result.isEmpty()) {
                        result = "0.0"
                    } else {
                        while (result[result.lastIndex].toString() in arrayOf(
                                "+", "-", "/", "*", "%"
                            )
                        ) {
                            result = result.removeRange(result.lastIndex, result.lastIndex + 1)
                            if (result.isEmpty()) {
                                result = "0.0"
                                return@setOnClickListener
                            }
                        }
                        try {

                            val expression: Expression = ExpressionBuilder(result).build()
                            val res: Double = expression.evaluate()
                            result = res.toString()
                        } catch (exception: IllegalArgumentException) {
                            showToast("Enter a valid Expression")
                        } catch (exp: ArithmeticException) {
                            showToast("Divide By Zero Not Allowed")
                        } catch (e: Exception) {
                            showToast("Some Error Occurred")
                        }
                    }
                }

                "<" -> {
                    if (result != "")
                        result = result.removeRange(result.lastIndex, result.lastIndex + 1)
                }

                "AC" -> {
                    result = ""
                }

                else -> {
                    result += holder.keys.text
                }
            }
            updateTextView(result)
        }
    }
}

class StringDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

