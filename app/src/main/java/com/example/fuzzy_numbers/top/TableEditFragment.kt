package com.example.fuzzy_numbers.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.FuzzyNumber
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.helpers.NotifyHelpers.Companion.showSnackBarNotify

class TableEditFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel
    private var tableIndex: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_edit)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val addButton: ImageButton = view.findViewById(R.id.add_button)
        val alphaEditText: EditText = view.findViewById(R.id.alphaEditText)
        val minEditText: EditText = view.findViewById(R.id.minEditText)
        val maxEditText: EditText = view.findViewById(R.id.maxEditText)

        viewModel.fuzzyNumbers.observe(viewLifecycleOwner, Observer { list ->
            val adapter = TableEditAdapter(list[tableIndex].slices) { alpha, min, max ->
                val updatedList = list[tableIndex].slices
                updatedList.find{it.alpha == alpha}?.max = max
                updatedList.find{it.alpha == alpha}?.min = min
                viewModel.updateFuzzyNumber(tableIndex, FuzzyNumber(list[tableIndex].name, updatedList))
            }
            recyclerView.adapter = adapter

            addButton.setOnClickListener {
                if (alphaEditText.text.isNullOrEmpty()) {
                    showSnackBarNotify(view,"Alpha cannot be empty", R.color.warning);
                    return@setOnClickListener
                }
                if (minEditText.text.isNullOrEmpty()) {
                    showSnackBarNotify(view,"Min value cannot be empty", R.color.warning);
                    return@setOnClickListener
                }
                if (maxEditText.text.isNullOrEmpty()) {
                    showSnackBarNotify(view,"Max value cannot be empty", R.color.warning);
                    return@setOnClickListener
                }
                val newAlpha = alphaEditText.text.toString().toDouble()

                if (newAlpha - 1.0f > 0.00000000001f) {
                    showSnackBarNotify(view, "Alpha must be less than or equal to 1.0", R.color.warning);
                    return@setOnClickListener
                }

                val newMin = minEditText.text.toString().toDouble()
                val newMax = maxEditText.text.toString().toDouble()
                val updatedList = list[tableIndex].slices
                updatedList.find{it.alpha == newAlpha}?.max = newMax
                updatedList.find{it.alpha == newAlpha}?.min = newMin
                viewModel.updateFuzzyNumber(tableIndex, FuzzyNumber(list[tableIndex].name, updatedList))
                adapter.notifyItemInserted(updatedList.size - 1)
                showSnackBarNotify(view,"Added", R.color.success)
            }
        })
    }

    companion object {
        fun newInstance(index: Int): TableEditFragment {
            val fragment = TableEditFragment()
            val args = Bundle()
            args.putInt("INDEX", index)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tableIndex = arguments?.getInt("INDEX") ?: 0
    }
}
