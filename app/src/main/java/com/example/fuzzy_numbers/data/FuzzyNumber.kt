package com.example.fuzzy_numbers.data

data class FuzzyNumber(val name: String, var values: List<Slice>)

data class Slice(val alpha: Double, var min: Double, var max: Double)
