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


class TableEditAdapter(
    private val values: MutableMap<Double, Pair<Int, Int>>,
    private val onItemChanged: (Double, Int, Int) -> Unit
) : RecyclerView.Adapter<TableEditAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alphaEditText: TextView = itemView.findViewById(R.id.alphaEditText)
        val minEditText: EditText = itemView.findViewById(R.id.minEditText)
        val maxEditText: EditText = itemView.findViewById(R.id.maxEditText)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(alpha: Double, minMax: Pair<Int, Int>) {
            alphaEditText.text = alpha.toString()
            minEditText.setText(minMax.first.toString())
            maxEditText.setText(minMax.second.toString())

            alphaEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) return
                    val newAlpha = s.toString().toDoubleOrNull()
                    if (newAlpha != null && minEditText.text.isNotEmpty() && maxEditText.text.isNotEmpty()) {
                        val min = minEditText.text.toString().toInt()
                        val max = maxEditText.text.toString().toInt()
                        onItemChanged(newAlpha, min, max)
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            minEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) return
                    val min = s.toString().toIntOrNull()
                    if (min != null && alphaEditText.text.isNotEmpty() && maxEditText.text.isNotEmpty()) {
                        val max = maxEditText.text.toString().toInt()
                        onItemChanged(alpha, min, max)
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            maxEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) return
                    val max = s.toString().toIntOrNull()
                    if (max != null && alphaEditText.text.isNotEmpty() && minEditText.text.isNotEmpty()) {
                        val min = minEditText.text.toString().toInt()
                        onItemChanged(alpha, min, max)
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            deleteButton.setOnClickListener {
                values.remove(alpha)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_edit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = values.keys.elementAt(position)
        holder.bind(key, values[key]!!)
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
