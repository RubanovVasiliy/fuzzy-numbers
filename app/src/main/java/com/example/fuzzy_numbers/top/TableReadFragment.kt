package com.example.fuzzy_numbers.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.google.android.material.snackbar.Snackbar

class TableReadFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel
    private lateinit var adapter: TableReadAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_read, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_read)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = TableReadAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.fuzzyNumbers.observe(viewLifecycleOwner, Observer { list ->
            if (list.isNotEmpty())
                adapter.updateData(list[2].values)
        })
    }
}
