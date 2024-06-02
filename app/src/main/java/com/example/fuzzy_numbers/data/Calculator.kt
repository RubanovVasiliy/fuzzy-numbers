package com.example.fuzzy_numbers.data

import com.example.fuzzy_numbers.R
import java.util.Dictionary

class Calculator {
/*
*//*    private fun setupChart(chart: LineChart) {
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

        val legend: Legend = chart.legend
        legend.form = Legend.LegendForm.LINE
    }*//*

    companion object {

        fun performOperation(operation: Operation, numberA: FuzzyNumber, numberB: FuzzyNumber, result: FuzzyNumber) {

            val commonAlphaLevels = (numberA.values.keys + numberB.values.keys).distinct().sorted()
            val alignedA = alignAlphaLevels(numberA.values, commonAlphaLevels)
            val alignedB = alignAlphaLevels(numberA.values, commonAlphaLevels)

            val result = when (operation) {
                Operation.ADD -> alignedA.zip(alignedB) { a, b ->
                    FuzzyNumber(
                        a.alpha,
                        a.left + b.left,
                        a.right + b.right
                    )
                }

                Operation.SUBTRACT -> alignedA.zip(alignedB) { a, b ->
                    FuzzyNumber(
                        a.alpha,
                        a.left - b.left,
                        a.right - b.right
                    )
                }

                Operation.MULTIPLY -> alignedA.zip(alignedB) { a, b ->
                    FuzzyNumber(
                        a.alpha,
                        a.left * b.left,
                        a.right * b.right
                    )
                }

                Operation.DIVIDE -> alignedA.zip(alignedB) { a, b ->
                    FuzzyNumber(
                        a.alpha,
                        a.left / b.left,
                        a.right / b.right
                    )
                }
            }
        }

        private fun alignAlphaLevels(numbers: MutableMap<Double, Pair<Int, Int>>, alphaLevels: List<Double>): List<FuzzyNumber> {
            val aligned = Dictionary<Double, Pair<Int, Int>>()
            for (alpha in alphaLevels) {
                val matching = numbers.keys.find { it == alpha }
                if (matching != null) {
                    aligned.add(matching)
                } else {
                    val lower = numbers.lastOrNull { it.alpha < alpha }
                    val upper = numbers.firstOrNull { it.alpha > alpha }
                    if (lower != null && upper != null) {
                        val left = interpolate(lower.alpha, lower.left, upper.alpha, upper.left, alpha)
                        val right = interpolate(lower.alpha, lower.right, upper.alpha, upper.right, alpha)
                        aligned.add(FuzzyNumber(alpha, left, right))
                    }
                }
            }
            return aligned
        }
    }

    private fun interpolate(x1: Float, y1: Float, x2: Float, y2: Float, x: Float): Float {
        return y1 + (y2 - y1) * (x - x1) / (x2 - x1)
    }

    private fun updateChart(chart: LineChart, setA: List<FuzzyNumber>, setB: List<FuzzyNumber>, result: List<FuzzyNumber>) {
        val entriesA = setA.map { Entry(it.alpha, (it.left  + it.right) / 2) }
        val entriesB = setB.map { Entry(it.alpha, (it.left + it.right) / 2) }
        val entriesResult = result.map { Entry(it.alpha, (it.left + it.right) / 2) }

        val dataSetA = LineDataSet(entriesA, "Set A").apply {
            color = resources.getColor(R.color.teal, null)
            setCircleColor(color)
        }
        val dataSetB = LineDataSet(entriesB, "Set B").apply {
            color = resources.getColor(R.color.purple, null)
            setCircleColor(color)
        }
        val dataSetResult = LineDataSet(entriesResult, "Result").apply {
            color = resources.getColor(R.color.orange, null)
            setCircleColor(color)
        }

        val lineData = LineData(dataSetA, dataSetB, dataSetResult)
        chart.data = lineData
        chart.invalidate()
    }*/

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}


