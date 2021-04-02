package com.example.vaseisapp.ui.dashboard.calculator

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.calculation.entities.CalculationEntity
import com.example.vaseisapp.domain.calculation.entities.ExamType
import com.example.vaseisapp.domain.calculation.entities.Group
import com.example.vaseisapp.domain.calculation.entities.Lesson

class CalculatorViewModel @ViewModelInject constructor() : BaseViewModel() {
    private var _dataUI = MutableLiveData<CalculationEntity>()
    val dataUI: LiveData<CalculationEntity> = _dataUI

    fun loadLessons() {
        val dumm = listOf(
            Lesson("1", "Οικονομιλων", "Μαθηματικά", 1.toDouble()),
            Lesson("2", "Οικονομιλων", "ΑΟΘ", 1.toDouble()),
            Lesson("3", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble()),
            Lesson("4", "Οικονομιλων", "Νεοελληνική Γλώσσα", 1.toDouble())
        )

        val dumm1 = listOf(
            Lesson("5", "Οικονομιλων", "Αρχαια", 1.toDouble()),
            Lesson("6", "Οικονομιλων", "Ιστορία", 1.toDouble()),
            Lesson("7", "Οικονομιλων", "Κείμενα", 1.toDouble()),
            Lesson("8", "Οικονομιλων", "Νεοελληνική Γλώσσα", 1.toDouble())
        )

        val dumm2 = listOf(
            Lesson("9", "Οικονομιλων", "Φυσική", 1.toDouble()),
            Lesson("10", "Οικονομιλων", "Μαθηματικά", 1.toDouble()),
            Lesson("11", "Οικονομιλων", "ΑΟΘ", 1.toDouble()),
            Lesson("12", "Οικονομιλων", "Νεοελληνική Γλώσσα", 1.toDouble())
        )

        val dumm3 = listOf(
            Lesson("13", "Οικονομιλων", "Τεστ1", 1.toDouble()),
            Lesson("14", "Οικονομιλων", "Μαθηματικά", 1.toDouble()),
            Lesson("15", "Οικονομιλων", "ΑΟΘ", 1.toDouble()),
            Lesson("16", "Οικονομιλων", "Νεοελληνική Γλώσσα", 1.toDouble())
        )

        val dummyGroups = listOf(
            Group("μαθηματικα", "Πεδίο 1", dumm, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1),
            Group("μαθηματικα", "Πεδίο 2", dumm1, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1),
            Group("μαθηματικα", "Πεδίο 3", dumm2, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1),
            Group("μαθηματικα", "Πεδίο 4", dumm3, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1)
        )
        val dummyGroups1 = listOf(
            Group("μαθηματικα", "Πεδίο 1", dumm, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1),
            Group("μαθηματικα", "Πεδίο 2", dumm1, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1)
        )
        val dummyGroups2 = listOf(
            Group("μαθηματικα", "Πεδίο 1", dumm, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1),
            Group("μαθηματικα", "Πεδίο 2", dumm1, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1),
            Group("μαθηματικα", "Πεδίο 3", dumm2, listOf(Lesson("1", "Οικονομιλων", "ΑΕΠΠ", 1.toDouble())), 1)
        )

        val dummyExamTypes = listOf(
            ExamType("1", "Γενικό Λύκειο", "ΓΕΛ", dummyGroups),
            ExamType("2", "Επαγγελματικό Λύκειο", "ΕΠΑΛ", dummyGroups2),
            ExamType("3", "Εσπερινό Λύκειο", "Εσπερινό", dummyGroups1)
        )

        val calculationEntity = CalculationEntity(dummyExamTypes)

        _dataUI.value = calculationEntity
    }
}