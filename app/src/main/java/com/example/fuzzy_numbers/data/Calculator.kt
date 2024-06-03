package com.example.fuzzy_numbers.data

import java.math.RoundingMode

class Calculator {

    companion object {

        private fun format(value: Double): Double {
            return value.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        }

        fun performOperation(operation: Operation, numberA: FuzzyNumber, numberB: FuzzyNumber, result: FuzzyNumber) {

            val commonAlphaLevels = (numberA.slices.map { it.alpha } + numberB.slices.map { it.alpha }).distinct().sorted()
            val alignedA = alignAlphaLevels(numberA.slices.sortedBy { it.alpha }, commonAlphaLevels)
            val alignedB = alignAlphaLevels(numberB.slices.sortedBy { it.alpha }, commonAlphaLevels)

            result.slices = when (operation) {
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
            return format(val1 + (val2 - val1) * ((alpha - alpha1) / (alpha2 - alpha1)))
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

                            val lowerBound = interpolate(
                                lowerAlpha,
                                upperAlpha,
                                lowerInterval.min,
                                upperInterval.min,
                                alpha
                            )
                            val upperBound = interpolate(
                                lowerAlpha,
                                upperAlpha,
                                lowerInterval.max,
                                upperInterval.max,
                                alpha
                            )

                            aligned.add(Slice(alpha, lowerBound, upperBound))
                            break
                        }
                    }
                }
            }
            return aligned
        }

        fun compare(numberA: FuzzyNumber, numberB: FuzzyNumber) : Comparison{
            val commonAlphaLevels = (numberA.slices.map { it.alpha } + numberB.slices.map { it.alpha }).distinct().sorted()
            val alignedA = alignAlphaLevels(numberA.slices.sortedBy { it.alpha }, commonAlphaLevels)
            val alignedB = alignAlphaLevels(numberB.slices.sortedBy { it.alpha }, commonAlphaLevels)

            val intervals = alignedA.zip(alignedB)

            val predicateGreater: (Pair<Slice, Slice>) -> Boolean =
                { (a, b) -> a.min < b.min && a.max > b.min }
            val predicateGreaterEqual: (Pair<Slice, Slice>) -> Boolean =
                { (a, b) -> a.min <= b.min && a.max >= b.min }
            val predicateLess: (Pair<Slice, Slice>) -> Boolean =
                { (a, b) -> a.min > b.min && a.max < b.min }
            val predicateLessEqual: (Pair<Slice, Slice>) -> Boolean =
                { (a, b) -> a.min >= b.min && a.max <= b.min }
            val predicateEqual: (Pair<Slice, Slice>) -> Boolean =
                { (a, b) -> a.min == b.min && a.max == b.min }
            val predicateNotEqual: (Pair<Slice, Slice>) -> Boolean =
                { (a, b) -> a.min != b.min || a.max != b.min }

            return Comparison(true, intervals.all(predicateGreater),
                    intervals.all(predicateGreaterEqual),
                    intervals.all(predicateLess),
                    intervals.all(predicateLessEqual),
                    intervals.all(predicateEqual),
                    intervals.any(predicateNotEqual)
            )
        }
    }

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}


