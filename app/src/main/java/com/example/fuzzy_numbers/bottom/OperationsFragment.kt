package com.example.fuzzy_numbers.bottom


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.Calculator.Operation
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.helpers.NotifyHelpers

class OperationsFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = view.findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = view.findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = view.findViewById<Button>(R.id.buttonDivide)

        buttonAdd.setOnClickListener {
            viewModel.calculate(Operation.ADD)
            NotifyHelpers.showSnackBarNotify(view, "The result was calculated", R.color.success)
        }
        buttonSubtract.setOnClickListener {
            viewModel.calculate(Operation.SUBTRACT)
            NotifyHelpers.showSnackBarNotify(view, "The result was calculated", R.color.success)
        }
        buttonMultiply.setOnClickListener {
            viewModel.calculate(Operation.MULTIPLY)
            NotifyHelpers.showSnackBarNotify(view, "The result was calculated", R.color.success)
        }
        buttonDivide.setOnClickListener {
            viewModel.calculate(Operation.DIVIDE)
            NotifyHelpers.showSnackBarNotify(view, "The result was calculated", R.color.success)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_operations, container, false)
    }
}