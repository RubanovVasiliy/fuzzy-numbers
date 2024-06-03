package com.example.fuzzy_numbers.top

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.Slice

class TableReadAdapter(private var values: List<Slice>) : RecyclerView.Adapter<TableReadAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alphaTextView: TextView = itemView.findViewById(R.id.alphaTextView)
        val minTextView: TextView = itemView.findViewById(R.id.minTextView)
        val maxTextView: TextView = itemView.findViewById(R.id.maxTextView)

        fun bind(slice: Slice) {
            alphaTextView.text = slice.alpha.toString()
            minTextView.text = slice.min.toString()
            maxTextView.text = slice.max.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_read, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
        return values.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newValues: List<Slice>) {
        values = newValues
        notifyDataSetChanged()
    }
}
