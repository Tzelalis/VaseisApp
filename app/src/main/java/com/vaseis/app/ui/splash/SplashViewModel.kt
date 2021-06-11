package com.vaseis.app.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.vaseis.app.domain.calculation.entities.CalculatorGroup
import com.vaseis.app.domain.calculation.entities.CalculatorLesson
import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.usecase.calculator.SaveAllExamsTypesLocal
import com.vaseis.app.usecase.prefs.GetLanguageUseCase
import kotlinx.coroutines.delay

class SplashViewModel @ViewModelInject constructor(
    private val saveAllExamsTypesLocal: SaveAllExamsTypesLocal,
    private val getLanguageUseCase: GetLanguageUseCase
) : BaseViewModel() {
    private var _showMainUi = MutableLiveData<Unit>()
    val showMainUi: LiveData<Unit> = _showMainUi

    private var _langPref = MutableLiveData<Language>()
    val langPref: LiveData<Language> = _langPref

    fun loadData() {
        launch(true) {
            delay(1000)
            _langPref.value = getLanguageUseCase() ?: Language.SYSTEM_DEFAULT
            saveAllExamsTypesLocal(dummyExamTypes)
            _showMainUi.value = Unit
        }
    }

    val dumm = listOf(
        CalculatorLesson(id = "0", fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "1", fullName = "Οικονομιλων", shortName = "Μαθηματικά", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "2", fullName = "Οικονομιλων", shortName = "ΑΟΘ", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "3", fullName = "Οικονομιλων", shortName = "ΑΕΠΠ", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "3", fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm1 = listOf(
        CalculatorLesson(id = "4", fullName = "Νέα Ελληνικά", shortName = "Ν. Ελληνικά", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "5", fullName = "Μαθηματικά (Άλγεβρα)", shortName = "Μαθηματικά", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "6", fullName = "Μάθημα Ειδικότητας 1", shortName = "Ειδικότητα 1", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "7", fullName = "Μάθημα Ειδικότητας 2", shortName = "Ειδικότητα 2", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "3", fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm2 = listOf(
        CalculatorLesson(id = "8", fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "9", fullName = "Οικονομιλων", shortName = "Μαθηματικά", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "10", fullName = "Οικονομιλων", shortName = "Χημεία", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "11", fullName = "Οικονομιλων", shortName = "Φυσική", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "3", fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm3 = listOf(
        CalculatorLesson(id = "12", fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "13", fullName = "Οικονομιλων", shortName = "Βιολογία", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "14", fullName = "Οικονομιλων", shortName = "Χημεία", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "15", fullName = "Οικονομιλων", shortName = "Φυσική", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "3", fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dumm4 = listOf(
        CalculatorLesson(id = "16", fullName = "Οικονομιλων", shortName = "Ν. Γλώσσα", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "17", fullName = "Οικονομιλων", shortName = "Αρχαία Ελληνικά ", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "18", fullName = "Οικονομιλων", shortName = "Ιστορία ", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "19", fullName = "Οικονομιλων", shortName = "Κοινωνιολογία ", gravity = 250.toDouble(), isMandatory = true),
        CalculatorLesson(id = "3", fullName = "Οικονομιλων", shortName = "Αγγλικά", gravity = 250.toDouble(), isMandatory = false)
    )
    val dummyGroups = listOf(
        CalculatorGroup(
            id = "1",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 1",
            mandatoryCalculatorLessons = dumm,
            optionalCount = 0,
        ),
        CalculatorGroup(
            id = "2",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 2",
            mandatoryCalculatorLessons = dumm2,
            optionalCount = 0,
        ),
        CalculatorGroup(
            id = "3",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 3",
            mandatoryCalculatorLessons = dumm3,
            optionalCount = 0,
        ),
        CalculatorGroup(
            id = "4",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 4",
            mandatoryCalculatorLessons = dumm4,
            optionalCount = 0,
        ),
    )

    val dummyGroups2 = listOf(
        CalculatorGroup(
            id = "0",
            fullName = "μαθηματικα",
            shortName = "Όλα",
            mandatoryCalculatorLessons = dumm1,
            optionalCount = 0,
        ),
    )

    val dummyGroups1 = listOf(
        CalculatorGroup(
            id = "5",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 1",
            mandatoryCalculatorLessons = dumm + dumm+ dumm+ dumm+ dumm+ dumm+ dumm+ dumm,
            optionalCount = 0,
        ),
        CalculatorGroup(
            id = "6",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 2",
            mandatoryCalculatorLessons = dumm2,
            optionalCount = 0,
        ),
        CalculatorGroup(
            id = "7",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 3",
            mandatoryCalculatorLessons = dumm3,
            optionalCount = 0,
        ),
        CalculatorGroup(
            id = "8",
            fullName = "μαθηματικα",
            shortName = "Πεδίο 4",
            mandatoryCalculatorLessons = dumm4,
            optionalCount = 0,
        ),
    )

    val dummyExamTypes = listOf(
        CalculatorExamType("3", "Γενικό Λύκειο", "ΓΕΛ", dummyGroups),
        CalculatorExamType("4", "Επαγγελματικό Λύκειο", "ΕΠΑΛ", dummyGroups2),
        CalculatorExamType("5", "Εσπερινό Λύκειο", "Εσπερινό", dummyGroups1),
    )
}