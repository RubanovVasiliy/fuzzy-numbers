package com.example.fuzzy_numbers.data

data class FuzzyNumber(val name: String, val values: List<Slice>)

data class Slice(val name: Double, var min: Int, var max: Int)
