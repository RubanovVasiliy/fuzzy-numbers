package com.example.fuzzy_numbers.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.helpers.NotifyHelpers

class ComparisonFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_comparison, container, false)


        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        val buttons = listOf(
            view.findViewById<Button>(R.id.buttonGreater),
            view.findViewById<Button>(R.id.buttonGreaterEqual),
            view.findViewById<Button>(R.id.buttonLess),
            view.findViewById<Button>(R.id.buttonLessEqual),
            view.findViewById<Button>(R.id.buttonEqual),
            view.findViewById<Button>(R.id.buttonNotEqual),
            view.findViewById<Button>(R.id.buttonCompare)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                Toast.makeText(activity, "Clicked: ${button.text}", Toast.LENGTH_SHORT).show()
            }
        }

        val buttonExchange = view.findViewById<Button>(R.id.buttonExchange)

        buttonExchange.setOnClickListener {
            viewModel.exchange()
            NotifyHelpers.showSnackBarNotify(view, "Values have been exchanged", R.color.success)
        }

        return view
    }
}
