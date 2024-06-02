package com.example.fuzzy_numbers.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class FuzzyNumberViewModel : ViewModel() {
    val fuzzyNumbers: MutableLiveData<List<FuzzyNumber>> = MutableLiveData()

    init {
        // Инициализация данных
        val list = listOf(
            FuzzyNumber(
                "Table 1",
                mutableMapOf(
                    0.0 to Pair(1, 9),
                    0.5 to Pair(2, 8),
                    1.0 to Pair(3, 4)
                )
            ),
            FuzzyNumber(
                "Table 2",
                mutableMapOf(
                    0.0 to Pair(1, 9),
                    0.5 to Pair(3, 6),
                    1.0 to Pair(4, 5),
                    0.2 to Pair(2, 7)
                )
            ),
            FuzzyNumber("Table 3", mutableMapOf())
        )
        fuzzyNumbers.value = list
    }

    fun updateFuzzyNumber(index: Int, fuzzyNumber: FuzzyNumber) {
        val currentList = fuzzyNumbers.value?.toMutableList()
        currentList?.set(index, fuzzyNumber)
        fuzzyNumbers.value = currentList
    }
}
