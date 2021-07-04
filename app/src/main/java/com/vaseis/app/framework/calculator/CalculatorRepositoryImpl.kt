package com.vaseis.app.framework.calculator

import android.content.SharedPreferences
import androidx.core.content.edit
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.vaseis.app.data.calculator.CalculatorRepository
import com.vaseis.app.data.calculator.model.RemoteCalculatorResponse
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.vaseis.app.utils.handleExternalApiFormat
import javax.inject.Inject


class CalculatorRepositoryImpl @Inject constructor(
    private val api: CalculatorApi,
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) : CalculatorRepository {

    override suspend fun saveDataLocal(listOfDummyCalculatorExamType: List<CalculatorExamType>) {
        sharedPreferences.edit {
            val listMyData = Types.newParameterizedType(MutableList::class.java, CalculatorExamType::class.java)
            val adapter: JsonAdapter<List<CalculatorExamType>> = moshi.adapter(listMyData)
            putString("CALCULATOR", adapter.toJson(listOfDummyCalculatorExamType))
        }.apply { }
    }

    override suspend fun getAllExamsTypes(): RemoteCalculatorResponse {
        val response = api.getAllCalculator()
        return response.handleExternalApiFormat()

/*        return withContext(Dispatchers.IO) {
            runCatching{
                val json = sharedPreferences.getString("CALCULATOR", "") ?: ""
                val listMyData = Types.newParameterizedType(MutableList::class.java, DummyCalculatorExamType::class.java)
                val adapter: JsonAdapter<List<DummyCalculatorExamType>> = moshi.adapter(listMyData)
                adapter.fromJson(json) ?: listOf()
            }.getOrNull() ?: emptyList()
        }*/

        /* return withContext(Dispatchers.IO){
             val json = sharedPreferences.getString("CALCULATOR", "") ?: ""
             val gson = Gson()
             val myType = object : TypeToken<List<CalculatorExamType>>() {}.type
             gson.fromJson(json, myType)
         }*/
    }
}