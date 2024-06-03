package com.example.fuzzy_numbers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fuzzy_numbers.data.FuzzyNumberViewModel
import com.example.fuzzy_numbers.databinding.ActivityGraphBinding
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class GraphActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGraphBinding
    private lateinit var viewModel: FuzzyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[FuzzyNumberViewModel::class.java]

        setupChart(binding.lineChart)
        drawChart(binding.lineChart)
    }

    private fun setupChart(chart: LineChart) {
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.setPinchZoom(true)

        val xAxis: XAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis: YAxis = chart.axisLeft
        leftAxis.setDrawGridLines(true)

        val rightAxis: YAxis = chart.axisRight
        rightAxis.setDrawGridLines(false)
    }

    private fun drawChart(chart: LineChart) {
        val fuzzyNumbers = viewModel.fuzzyNumbers.value ?: return
        val result = fuzzyNumbers[2]

        val entries = result.slices.map { Entry(it.min.toFloat(), it.max.toFloat()) }

        val dataSet = LineDataSet(entries, "Result")
        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate()
    }
}