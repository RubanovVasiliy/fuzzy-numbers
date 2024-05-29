package com.example.fuzzy_numbers.table


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fuzzy_numbers.FuzzyNumber
import com.example.fuzzy_numbers.MyAdapter
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.databinding.FragmentTableBinding
import com.google.android.material.snackbar.Snackbar


class TableFragment : Fragment() {

    private lateinit var binding: FragmentTableBinding
    private lateinit var adapter: MyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.textView.text = arguments?.getString("text")

        val message: String = if (arguments == null) "null" else "not null"
        //Snackbar.make(view.rootView ,message, Snackbar.LENGTH_LONG).show()


        val fuzzyNumber = FuzzyNumber("Example",
            mutableMapOf(
            0.0 to Pair(2, 8),
            0.2 to Pair(3, 5),
            1.0 to Pair(4, 4)
        ))

        adapter = MyAdapter(fuzzyNumber)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            adapter.addItem(0.0, 0, 0)
        }
    }

    companion object {
        fun newInstance(text: String) =
            TableFragment().apply {
                arguments = Bundle().apply {
                    putString("text", text)
                }
            }
    }
}