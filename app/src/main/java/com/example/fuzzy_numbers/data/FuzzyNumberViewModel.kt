package com.example.fuzzy_numbers.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class FuzzyNumberViewModel : ViewModel() {
    val fuzzyNumbers: MutableLiveData<List<FuzzyNumber>> = MutableLiveData()

    init {
        // Инициализация данных
        val list = listOf(
            FuzzyNumber("Table 1", mutableMapOf(0.1 to Pair(1, 2))),
            FuzzyNumber("Table 2", mutableMapOf(0.2 to Pair(2, 3))),
            FuzzyNumber("Table 3", mutableMapOf(0.3 to Pair(3, 4)))
        )
        fuzzyNumbers.value = list
    }

    fun updateFuzzyNumber(index: Int, fuzzyNumber: FuzzyNumber) {
        val currentList = fuzzyNumbers.value?.toMutableList()
        currentList?.set(index, fuzzyNumber)
        fuzzyNumbers.value = currentList
    }
}
