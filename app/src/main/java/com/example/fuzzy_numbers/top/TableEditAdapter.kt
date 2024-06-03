package com.example.fuzzy_numbers.top

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.Slice


class TableEditAdapter(private var values: List<Slice>, private val onItemChanged: (Double, Double, Double) -> Unit) : RecyclerView.Adapter<TableEditAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alphaEditText: TextView = itemView.findViewById(R.id.alphaEditText)
        val minEditText: EditText = itemView.findViewById(R.id.minEditText)
        val maxEditText: EditText = itemView.findViewById(R.id.maxEditText)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(slice: Slice) {
            alphaEditText.text = slice.alpha.toString()
            minEditText.setText(slice.min.toString())
            maxEditText.setText(slice.max.toString())

            alphaEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) return
                    val newAlpha = s.toString().toDoubleOrNull()
                    if (newAlpha != null && minEditText.text.isNotEmpty() && maxEditText.text.isNotEmpty()) {
                        val min = minEditText.text.toString().toDouble()
                        val max = maxEditText.text.toString().toDouble()
                        onItemChanged(newAlpha, min, max)
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            minEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) return
                    val min = s.toString().toDoubleOrNull()
                    if (min != null && alphaEditText.text.isNotEmpty() && maxEditText.text.isNotEmpty()) {
                        val max = maxEditText.text.toString().toDouble()
                        onItemChanged(slice.alpha, min, max)
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            maxEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) return
                    val max = s.toString().toDoubleOrNull()
                    if (max != null && alphaEditText.text.isNotEmpty() && minEditText.text.isNotEmpty()) {
                        val min = minEditText.text.toString().toDouble()
                        onItemChanged(slice.alpha, min, max)
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            deleteButton.setOnClickListener {
                values = values.filter { it.alpha != slice.alpha }
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_edit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
