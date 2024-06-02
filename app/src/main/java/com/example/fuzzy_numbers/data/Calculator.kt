package com.example.fuzzy_numbers.data

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

        fun performOperation(operation: Operation, numberA: FuzzyNumber, numberB: FuzzyNumber, result: FuzzyNumber) {

            val commonAlphaLevels = (numberA.values.map { it.alpha } + numberB.values.map { it.alpha }).distinct().sorted()
            val alignedA = alignAlphaLevels(numberA.values.sortedBy { it.alpha }, commonAlphaLevels)
            val alignedB = alignAlphaLevels(numberA.values, commonAlphaLevels)

            result.values = when (operation) {
                Operation.ADD -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, a.min + b.min, a.max + b.max)
                }

                Operation.SUBTRACT -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, a.min - b.min, a.max - b.max)
                }

                Operation.MULTIPLY -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, a.min * b.min, a.max * b.max)
                }

                Operation.DIVIDE -> alignedA.zip(alignedB) { a, b ->
                    Slice(a.alpha, a.min / b.min, a.max / b.max)
                }
            }
        }

        private fun interpolate(alpha1: Double, alpha2: Double, val1: Double, val2: Double, alpha: Double): Double {
            return val1 + (val2 - val1) * ((alpha - alpha1) / (alpha2 - alpha1))
        }

        private fun alignAlphaLevels(numbers: List<Slice>, commonAlphaLevels: List<Double>): List<Slice> {

            val aligned = mutableListOf<Slice>()
            val alphaLevels = numbers.map { it.alpha }

            for (alpha in commonAlphaLevels) {
                if (alpha in alphaLevels) {
                    val idx = alphaLevels.indexOf(alpha)
                    aligned.add(numbers[idx])
                } else {
                    // Найти два уровня альфа, между которыми нужно интерполировать
                    for (i in 0 until commonAlphaLevels.size - 1) {
                        if (commonAlphaLevels[i] < alpha && alpha < commonAlphaLevels[i + 1]) {
                            val lowerAlpha = commonAlphaLevels[i]
                            val upperAlpha = commonAlphaLevels[i + 1]
                            val lowerInterval = intervals[i]
                            val upperInterval = intervals[i + 1]

                            val lowerBound = interpolate(lowerAlpha, upperAlpha, lowerInterval[0], upperInterval[0], alpha)
                            val upperBound = interpolate(lowerAlpha, upperAlpha, lowerInterval[1], upperInterval[1], alpha)

                            aligned.add(listOf(lowerBound, upperBound))
                            break
                        }
                    }
                }
            }

            return aligned
        }


        /*private fun alignAlphaLevels(numbers: List<Slice>, alphaLevels: List<Double>): List<Slice> {
            val aligned = mutableListOf<Slice>()
            for (alpha in alphaLevels) {
                val matching = numbers.find { it.alpha == alpha }
                if (matching != null) {
                    aligned.add(matching)
                } else {
                    for ( i in alphaLevels.indices){
                        if (alphaLevels[i] < alpha < alphaLevels[i + 1]) {
                            lower_alpha = alpha_levels[i]
                            upper_alpha = alpha_levels[i + 1]
                            lower_interval = intervals[i]
                            upper_interval = intervals[i + 1]

                            lower_bound = interpolate(lower_alpha, upper_alpha, lower_interval[0], upper_interval[0], alpha)
                            upper_bound = interpolate(lower_alpha, upper_alpha, lower_interval[1], upper_interval[1], alpha)

                            interpolated_intervals.append([lower_bound, upper_bound])
                            break
                        }
                    }



                }
            }
            return aligned
        }*/
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


