package com.example.fuzzy_numbers.bottom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fuzzy_numbers.GraphActivity
import com.example.fuzzy_numbers.R
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.helpers.NotifyHelpers.Companion.showSnackBarNotify

class GraphFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        val buttonBuild = view.findViewById<Button>(R.id.buttonBuild)
        val buttonExchange = view.findViewById<Button>(R.id.buttonExchange)

        buttonBuild.setOnClickListener {
            val intent = Intent(activity, GraphActivity::class.java)
            startActivity(intent)
        }

        buttonExchange.setOnClickListener {
            viewModel.exchange()
            showSnackBarNotify(view, "Values have been exchanged", R.color.success)
        }
    }
}
