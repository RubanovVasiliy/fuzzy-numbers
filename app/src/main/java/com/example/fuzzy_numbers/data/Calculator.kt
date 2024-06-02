package com.example.fuzzy_numbers.data

import java.math.RoundingMode

class Calculator {

/*    private fun setupChart(chart: LineChart) {
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
    }*/

    companion object {

        private fun format(value: Double): Double{
            return value.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        }

        fun performOperation(operation: Operation, numberA: FuzzyNumber, numberB: FuzzyNumber, result: FuzzyNumber) {

            val commonAlphaLevels = (numberA.values.map { it.alpha } + numberB.values.map { it.alpha }).distinct().sorted()
            val alignedA = alignAlphaLevels(numberA.values.sortedBy { it.alpha }, commonAlphaLevels)
            val alignedB = alignAlphaLevels(numberB.values.sortedBy { it.alpha }, commonAlphaLevels)

            result.values = when (operation) {
                Operation.ADD -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, a.min + b.min, a.max + b.max)
                }

                Operation.SUBTRACT -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, format(a.min - b.min), format(a.max - b.max))
                }

                Operation.MULTIPLY -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, format(a.min * b.min), format(a.max * b.max))
                }

                Operation.DIVIDE -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, format(a.min / b.min), format(a.max / b.max))
                }
            }
        }

        private fun interpolate(alpha1: Double, alpha2: Double, val1: Double, val2: Double, alpha: Double): Double {
            return (val1 + (val2 - val1) * ((alpha - alpha1) / (alpha2 - alpha1)))
                .toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        }

        private fun alignAlphaLevels(numbers: List<Slice>, commonAlphaLevels: List<Double>): List<Slice> {

            val aligned = mutableListOf<Slice>()
            val alphaLevels = numbers.map { it.alpha }

            for (alpha in commonAlphaLevels) {
                if (alpha in alphaLevels) {
                    val idx = alphaLevels.indexOf(alpha)
                    aligned.add(numbers[idx])
                } else {
                    for (i in 0 until alphaLevels.size - 1) {
                        if (alphaLevels[i] < alpha && alpha < alphaLevels[i + 1]) {
                            val lowerAlpha = alphaLevels[i]
                            val upperAlpha = alphaLevels[i + 1]
                            val lowerInterval = numbers[i]
                            val upperInterval = numbers[i + 1]

                            val lowerBound = interpolate(lowerAlpha, upperAlpha, lowerInterval.min, upperInterval.min, alpha)
                            val upperBound = interpolate(lowerAlpha, upperAlpha, lowerInterval.max, upperInterval.max, alpha)

                            aligned.add(Slice(alpha, lowerBound, upperBound))
                            break
                        }
                    }
                }
            }

            return aligned
        }
    }

    /*private fun updateChart(chart: LineChart, setA: List<FuzzyNumber>, setB: List<FuzzyNumber>, result: List<FuzzyNumber>) {
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


