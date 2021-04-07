package com.example.vaseisapp.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.calculation.entities.ExamType
import com.example.vaseisapp.domain.calculation.entities.Group
import com.example.vaseisapp.domain.calculation.entities.Lesson
import com.example.vaseisapp.domain.prefs.Language
import com.example.vaseisapp.usecase.calculator.SaveAllExamsTypesLocal
import com.example.vaseisapp.usecase.prefs.GetLanguageUseCase

class SplashViewModel @ViewModelInject constructor(
    private val saveAllExamsTypesLocal: SaveAllExamsTypesLocal,
    private val getLanguageUseCase: GetLanguageUseCase
) : BaseViewModel() {
    private var _showMainUi = MutableLiveData<Unit>()
    val showMainUi: LiveData<Unit> = _showMainUi

    private var _langPref = MutableLiveData<Language>()
    val langPref: LiveData<Language> = _langPref

    fun loadLanguage() {
        launch(true) {
            _langPref.value = getLanguageUseCase() ?: Language.SYSTEM_DEFAULT
        }
    }

    fun loadCalculator() {
        launch(true) {
            saveAllExamsTypesLocal(dummyExamTypes)
            _showMainUi.value = Unit
        }

    }

    val dumm = listOf(
        Lesson(id = 0, fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 1, fullName = "Οικονομιλων", shortName = "Μαθηματικά", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 2, fullName = "Οικονομιλων", shortName = "ΑΟΘ", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 3, fullName = "Οικονομιλων", shortName = "ΑΕΠΠ", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 3, fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm1 = listOf(
        Lesson(id = 4, fullName = "Νέα Ελληνικά", shortName = "Ν. Ελληνικά", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 5, fullName = "Μαθηματικά (Άλγεβρα)", shortName = "Μαθηματικά", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 6, fullName = "Μάθημα Ειδικότητας 1", shortName = "Ειδικότητα 1", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 7, fullName = "Μάθημα Ειδικότητας 2", shortName = "Ειδικότητα 2", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 3, fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm2 = listOf(
        Lesson(id = 8, fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 9, fullName = "Οικονομιλων", shortName = "Μαθηματικά", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 10, fullName = "Οικονομιλων", shortName = "Χημεία", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 11, fullName = "Οικονομιλων", shortName = "Φυσική", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 3, fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm3 = listOf(
        Lesson(id = 12, fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 13, fullName = "Οικονομιλων", shortName = "Βιολογία", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 14, fullName = "Οικονομιλων", shortName = "Χημεία", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 15, fullName = "Οικονομιλων", shortName = "Φυσική", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 3, fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm4 = listOf(
        Lesson(id = 16, fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 17, fullName = "Οικονομιλων", shortName = "Αρχαία Ελληνικά ", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 18, fullName = "Οικονομιλων", shortName = "Ιστορία ", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 19, fullName = "Οικονομιλων", shortName = "Κοινωνιολογία ", gravity = 250.toDouble(), isMandatory = true),
        Lesson(id = 3, fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dummyGroups = listOf(
        Group(
            id = "1",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 1",
            mandatoryLessons = dumm,
            optionalCount = 0,
        ),
        Group(
            id = "2",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 2",
            mandatoryLessons = dumm2,
            optionalCount = 0,
        ),
        Group(
            id = "3",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 3",
            mandatoryLessons = dumm3,
            optionalCount = 0,
        ),
        Group(
            id = "4",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 4",
            mandatoryLessons = dumm4,
            optionalCount = 0,
        ),
    )

    val dummyGroups2 = listOf(
        Group(
            id = "0",
            fullName = "μαθηματικα",
            shortName = "Όλα",
            mandatoryLessons = dumm1,
            optionalCount = 0,
        ),
    )

    val dummyGroups1 = listOf(
        Group(
            id = "5",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 1",
            mandatoryLessons = dumm,
            optionalCount = 0,
        ),
        Group(
            id = "6",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 2",
            mandatoryLessons = dumm2,
            optionalCount = 0,
        ),
        Group(
            id = "7",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 3",
            mandatoryLessons = dumm3,
            optionalCount = 0,
        ),
        Group(
            id = "8",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 4",
            mandatoryLessons = dumm4,
            optionalCount = 0,
        ),
    )

    val dummyExamTypes = listOf(
        ExamType("3", "Γενικό Λύκειο", "ΓΕΛ", dummyGroups),
        ExamType("4", "Επαγγελματικό Λύκειο", "ΕΠΑΛ", dummyGroups2),
        ExamType("5", "Εσπερινό Λύκειο", "Εσπερινό", dummyGroups1),
    )
}