package com.example.fuzzy_numbers


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class OperationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_operations, container, false)

        val buttons = listOf(
            view.findViewById<Button>(R.id.buttonAdd),
            view.findViewById<Button>(R.id.buttonSubtract),
            view.findViewById<Button>(R.id.buttonMultiply),
            view.findViewById<Button>(R.id.buttonDivide)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                Snackbar.make(view,"Clicked: ${button.text}",Snackbar.LENGTH_SHORT).show()
            }
        }

        return view
    }
}