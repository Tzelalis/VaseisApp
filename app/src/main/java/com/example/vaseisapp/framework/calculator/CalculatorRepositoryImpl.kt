package com.example.vaseisapp.framework.calculator

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.vaseisapp.data.calculator.CalculatorRepository
import com.example.vaseisapp.domain.calculation.entities.ExamType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CalculatorRepositoryImpl(
    private val calculatorApi: CalculatorApi,
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) : CalculatorRepository {

    override suspend fun fetchExamsTypes() {

    }

    override suspend fun saveDataLocal(listOfExamType: List<ExamType>) {
        sharedPreferences.edit {
            val listMyData = Types.newParameterizedType(MutableList::class.java, ExamType::class.java)
            val adapter: JsonAdapter<List<ExamType>> = moshi.adapter(listMyData)
            putString("CALCULATOR", adapter.toJson(listOfExamType))
        }.apply { }
    }

    override suspend fun getAllExamsTypes(): List<ExamType> {
        val json = sharedPreferences.getString("CALCULATOR", "") ?: ""
        val listMyData = Types.newParameterizedType(MutableList::class.java, ExamType::class.java)
        val adapter: JsonAdapter<List<ExamType>> = moshi.adapter(listMyData)

        return withContext(Dispatchers.IO) {
            adapter.fromJson(json) ?: listOf()
        }
    }
}