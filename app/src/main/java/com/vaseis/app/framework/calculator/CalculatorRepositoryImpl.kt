package com.vaseis.app.framework.calculator

import android.content.SharedPreferences
import androidx.core.content.edit
import com.vaseis.app.data.calculator.CalculatorRepository
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CalculatorRepositoryImpl @Inject constructor(
    private val api: CalculatorApi,
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) : CalculatorRepository {

    override suspend fun saveDataLocal(listOfCalculatorExamType: List<CalculatorExamType>) {
        sharedPreferences.edit {
            val listMyData = Types.newParameterizedType(MutableList::class.java, CalculatorExamType::class.java)
            val adapter: JsonAdapter<List<CalculatorExamType>> = moshi.adapter(listMyData)
            putString("CALCULATOR", adapter.toJson(listOfCalculatorExamType))
        }.apply { }
    }

    override suspend fun getAllExamsTypes(): List<CalculatorExamType> {
/*        return withContext(Dispatchers.IO) {
            runCatching{
                val json = sharedPreferences.getString("CALCULATOR", "") ?: ""
                val listMyData = Types.newParameterizedType(MutableList::class.java, CalculatorExamType::class.java)
                val adapter: JsonAdapter<List<CalculatorExamType>> = moshi.adapter(listMyData)
                adapter.fromJson(json) ?: listOf()
            }.getOrNull() ?: emptyList()
        }*/

        return withContext(Dispatchers.IO){
            val json = sharedPreferences.getString("CALCULATOR", "") ?: ""
            val gson = Gson()
            val myType = object : TypeToken<List<CalculatorExamType>>() {}.type
            gson.fromJson(json, myType)
        }
    }
}