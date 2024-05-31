package com.example.fuzzy_numbers.bottom


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fuzzy_numbers.R

class GraphFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_graph, container, false)

        val buttonBuild = view.findViewById<Button>(R.id.buttonBuild)
        val buttonExchange = view.findViewById<Button>(R.id.buttonExchange)

        buttonBuild.setOnClickListener {
            Toast.makeText(activity, "Clicked: Построить", Toast.LENGTH_SHORT).show()
        }

        buttonExchange.setOnClickListener {
            Toast.makeText(activity, "Clicked: Обменять значения", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}