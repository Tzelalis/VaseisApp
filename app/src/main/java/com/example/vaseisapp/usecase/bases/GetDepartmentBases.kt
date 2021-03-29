package com.example.vaseisapp.usecase.bases

import com.example.vaseisapp.domain.bases.DepartmentBases
import com.example.vaseisapp.domain.bases.BasesDataSource

class GetDepartmentBases(private val dataSource: BasesDataSource){
    suspend operator fun invoke(code : String) : List<DepartmentBases>{
        return dataSource.getBases(code)
    }
}