package com.example.fuzzy_numbers.data

data class FuzzyNumber(val name: String, val values: MutableMap<Double, Pair<Int, Int>>)
