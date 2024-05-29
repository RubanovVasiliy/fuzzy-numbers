package com.example.fuzzy_numbers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val items: MutableList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alphaEditText: EditText = itemView.findViewById(R.id.alphaEditText)
        val minEditText: EditText = itemView.findViewById(R.id.minEditText)
        val maxEditText: EditText = itemView.findViewById(R.id.maxEditText)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.alphaEditText.setText(items[position])

        holder.deleteButton.setOnClickListener {
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: String) {
        if (itemCount > 5) return
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}
