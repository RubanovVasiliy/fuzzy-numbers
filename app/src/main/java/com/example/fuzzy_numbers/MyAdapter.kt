package com.example.fuzzy_numbers

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val fuzzyNumber: FuzzyNumber) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editTextAlpha: EditText = itemView.findViewById(R.id.editTextAlpha)
        val editTextMin: EditText = itemView.findViewById(R.id.editTextMin)
        val editTextMax: EditText = itemView.findViewById(R.id.editTextMax)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = fuzzyNumber.values.entries.elementAt(position)
        val alpha = entry.key
        val minMax = entry.value

        holder.editTextAlpha.setText(alpha.toString())
        holder.editTextMin.setText(minMax.first.toString())
        holder.editTextMax.setText(minMax.second.toString())

        holder.deleteButton.setOnClickListener {
            fuzzyNumber.values.remove(alpha)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, fuzzyNumber.values.size)
        }

        holder.editTextAlpha.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newAlpha = s.toString().toDoubleOrNull()
                if (newAlpha != null) {
                    val newEntry = Pair(minMax.first, minMax.second)
                    fuzzyNumber.values.remove(alpha)
                    fuzzyNumber.values[newAlpha] = newEntry
                    notifyItemChanged(position)
                }
            }
        })

        holder.editTextMin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newMin = s.toString().toIntOrNull()
                if (newMin != null) {
                    fuzzyNumber.values[alpha] = Pair(newMin, minMax.second)
                }
            }
        })

        holder.editTextMax.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newMax = s.toString().toIntOrNull()
                if (newMax != null) {
                    fuzzyNumber.values[alpha] = Pair(minMax.first, newMax)
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return fuzzyNumber.values.size
    }

    fun addItem(alpha: Double, min: Int, max: Int) {
        if (itemCount > 5) return
        fuzzyNumber.values[alpha] = Pair(min, max)
        notifyItemInserted(fuzzyNumber.values.size - 1)
    }
}
