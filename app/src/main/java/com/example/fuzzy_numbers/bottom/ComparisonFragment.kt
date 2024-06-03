package com.example.fuzzy_numbers.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.helpers.NotifyHelpers

class ComparisonFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_comparison, container, false)

        val buttonGreater = view.findViewById<Button>(R.id.buttonGreater)
        val buttonGreaterEqual = view.findViewById<Button>(R.id.buttonGreaterEqual)
        val buttonLess = view.findViewById<Button>(R.id.buttonLess)
        val buttonLessEqual = view.findViewById<Button>(R.id.buttonLessEqual)
        val buttonEqual = view.findViewById<Button>(R.id.buttonEqual)
        val buttonNotEqual = view.findViewById<Button>(R.id.buttonNotEqual)

        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        viewModel.comparison.observe(viewLifecycleOwner, Observer {
            run {
                if (viewModel.comparison.value!!.isCompared) {
                    val buttonGreaterColor = if (!viewModel.comparison.value!!.isGreater) R.color.warning else R.color.success
                    val buttonGreaterEqualColor = if (!viewModel.comparison.value!!.isGreaterEqual) R.color.warning else R.color.success
                    val buttonLessColor = if (!viewModel.comparison.value!!.isLess) R.color.warning else R.color.success
                    val buttonLessEqualColor = if (!viewModel.comparison.value!!.isLessEqual) R.color.warning else R.color.success
                    val buttonEqualColor = if (!viewModel.comparison.value!!.isEqual) R.color.warning else R.color.success
                    val buttonNotEqualColor = if (!viewModel.comparison.value!!.isNotEqual) R.color.warning else R.color.success

                    buttonGreater.setBackgroundColor(
                        ContextCompat.getColor(view.context, buttonGreaterColor)
                    );
                    buttonGreaterEqual.setBackgroundColor(
                        ContextCompat.getColor(view.context, buttonGreaterEqualColor)
                    );
                    buttonLess.setBackgroundColor(
                        ContextCompat.getColor(view.context, buttonLessColor)
                    );
                    buttonLessEqual.setBackgroundColor(
                        ContextCompat.getColor(view.context, buttonLessEqualColor)
                    );
                    buttonEqual.setBackgroundColor(
                        ContextCompat.getColor(view.context, buttonEqualColor)
                    );
                    buttonNotEqual.setBackgroundColor(
                        ContextCompat.getColor(view.context, buttonNotEqualColor)
                    );
                }
            }
        })

        val buttonExchange = view.findViewById<Button>(R.id.buttonExchange)

        buttonExchange.setOnClickListener {
            viewModel.exchange()
            NotifyHelpers.showSnackBarNotify(view, "Values have been exchanged", R.color.success)
        }

        val buttonCompare = view.findViewById<Button>(R.id.buttonCompare)

        buttonCompare.setOnClickListener {
            viewModel.compare()
        }

        return view
    }
}
