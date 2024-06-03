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
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.data.Plotter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class PlotResultFragment : Fragment() {

    private lateinit var viewModel: FuzzyNumberViewModel
    private lateinit var lineChart: LineChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_plot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lineChart = view.findViewById(R.id.lineChart)

        viewModel = ViewModelProvider(requireActivity())[FuzzyNumberViewModel::class.java]

        Plotter.setupChart(lineChart)

        viewModel.fuzzyNumbers.observe(viewLifecycleOwner, Observer { list ->
            drawChart(lineChart)
        })
    }


    private fun drawChart(chart: LineChart) {
        chart.clear()
        val fuzzyNumbers = viewModel.fuzzyNumbers.value ?: return
        val result = fuzzyNumbers[2]

        val entries1 = result.slices.map { Entry(it.max.toFloat(), it.alpha.toFloat()) }
        val entries2 = result.slices.map { Entry(it.min.toFloat(), it.alpha.toFloat()) }

        val dataSet = LineDataSet(entries1 + entries2.reversed(), "Result")
        dataSet.lineWidth = 5f
        dataSet.color = R.color.warning
        dataSet.setDrawCircles(true)

        val lineData = LineData(dataSet)

        chart.data = lineData
        chart.invalidate()
    }
}
