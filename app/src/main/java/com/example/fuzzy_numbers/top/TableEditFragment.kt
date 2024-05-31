package com.example.fuzzy_numbers.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.FuzzyNumber
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel

class TableEditFragment : Fragment() {
    private lateinit var viewModel: FuzzyNumberViewModel
    private var tableIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_table_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FuzzyNumberViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.fuzzyNumbers.observe(viewLifecycleOwner, Observer { list ->
            val adapter = FuzzyNumberAdapter(list[tableIndex].values) { alpha, min, max ->
                val updatedList = list[tableIndex].values.toMutableMap()
                updatedList[alpha] = Pair(min, max)
                viewModel.updateFuzzyNumber(tableIndex, FuzzyNumber(list[tableIndex].name, updatedList))
            }
            recyclerView.adapter = adapter
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
