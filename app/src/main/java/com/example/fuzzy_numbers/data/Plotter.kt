package com.example.fuzzy_numbers.data

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis

class Plotter {
    companion object {
        fun setupChart(chart: LineChart) {
            chart.description.isEnabled = false
            chart.setTouchEnabled(true)
            chart.isDragEnabled = true
            chart.setScaleEnabled(true)
            chart.setPinchZoom(true)

            val xAxis: XAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            val leftAxis: YAxis = chart.axisLeft
            leftAxis.setDrawGridLines(false)

            val rightAxis: YAxis = chart.axisRight
            rightAxis.setDrawGridLines(false)
        }
    }
}