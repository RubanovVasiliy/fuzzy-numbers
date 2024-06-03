package com.example.fuzzy_numbers.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FuzzyNumberViewModel : ViewModel() {
    private val _fuzzyNumbers = MutableLiveData<List<FuzzyNumber>>()

    val fuzzyNumbers: LiveData<List<FuzzyNumber>> get() = _fuzzyNumbers

    init {
        _fuzzyNumbers.value = listOf(
            FuzzyNumber(
                "Table 1",
                listOf(
                    Slice(0.0, 1.0, 9.0),
                    Slice(0.5, 2.0, 8.0),
                    Slice(1.0, 3.0, 4.0),
                )
            ),
            FuzzyNumber(
                "Table 2",
                listOf(
                    Slice(0.0, 1.0, 9.0),
                    Slice(0.5, 3.0, 6.0),
                    Slice(1.0, 4.0, 4.0),
                    Slice(0.2, 2.0, 7.0),
                )
            ),
            FuzzyNumber("Table 3", listOf())
        )
    }

    fun updateFuzzyNumber(index: Int, fuzzyNumber: FuzzyNumber) {
        val currentList = _fuzzyNumbers.value?.toMutableList()
        currentList?.set(index, fuzzyNumber)
        _fuzzyNumbers.value = currentList
    }

    fun updateFuzzyNumbers() {
        _fuzzyNumbers.value = fuzzyNumbers.value
    }

    fun calculate(operation: Calculator.Operation): Int {
        val currentList = fuzzyNumbers.value?.toMutableList()

        if (currentList == null || currentList.size != 3)
            return -1

        if (currentList[0].slices.isEmpty())
            return 1

        if (currentList[1].slices.isEmpty())
            return 2

        Calculator.performOperation(operation, currentList[0], currentList[1], currentList[2])
        updateFuzzyNumbers()
        return 0
    }

    fun exchange() : Int {
        val currentList = fuzzyNumbers.value?.toMutableList()

        if (currentList == null || currentList.size != 3)
            return -1

        val temp = currentList[0].slices
        currentList[0].slices = currentList[1].slices
        currentList[1].slices = temp

        updateFuzzyNumbers()
        return 0
    }
}
