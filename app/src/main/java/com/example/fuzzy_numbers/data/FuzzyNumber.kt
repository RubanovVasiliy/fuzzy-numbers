package com.example.fuzzy_numbers.data

data class FuzzyNumber(val name: String, var slices: List<Slice>)

data class Slice(val alpha: Double, var min: Double, var max: Double)

data class Comparison(
    val isCompared: Boolean = false,
    val  isGreater:Boolean = false,
    val  isGreaterEqual:Boolean = false,
    val  isLess:Boolean = false,
    val  isLessEqual:Boolean = false,
    val  isEqual:Boolean = false,
    val  isNotEqual:Boolean = false,
)
