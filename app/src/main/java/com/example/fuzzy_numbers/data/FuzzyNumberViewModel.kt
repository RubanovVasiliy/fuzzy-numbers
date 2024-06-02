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
                listOf(
                    Slice(0.0,1,9),
                    Slice(0.5,2,8),
                    Slice(1.0,3,4),
                    )
            ),
            FuzzyNumber(
                "Table 2",
                listOf(
                    Slice(0.0,1,9),
                    Slice(0.5,3,6),
                    Slice(1.0,4,5),
                    Slice(0.2,2,7),
                )
            ),
            FuzzyNumber("Table 3", listOf())
        )
        fuzzyNumbers.value = list
    }

    fun updateFuzzyNumber(index: Int, fuzzyNumber: FuzzyNumber) {
        val currentList = fuzzyNumbers.value?.toMutableList()
        currentList?.set(index, fuzzyNumber)
        fuzzyNumbers.value = currentList
    }

    fun calculate(operation: Calculator.Operation):Int {
        val currentList = fuzzyNumbers.value?.toMutableList()

        if  (currentList == null) return -1
        if (currentList[0].values.isEmpty()) return 1
        if (currentList[1].values.isEmpty()) return 2

        //Calculator.performOperation(operation, currentList[0], currentList[1], currentList[2])
        return 0
    }
}
