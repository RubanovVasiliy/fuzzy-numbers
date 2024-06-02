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

class OperationsFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = view.findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = view.findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = view.findViewById<Button>(R.id.buttonDivide)

        buttonAdd.setOnClickListener { viewModel.calculate(Operation.ADD)  }
        buttonSubtract.setOnClickListener { viewModel.calculate(Operation.SUBTRACT) }
        buttonMultiply.setOnClickListener { viewModel.calculate(Operation.MULTIPLY) }
        buttonDivide.setOnClickListener { viewModel.calculate(Operation.DIVIDE) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_operations, container, false)
    }
}