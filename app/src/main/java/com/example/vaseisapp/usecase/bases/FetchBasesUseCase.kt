package com.example.vaseisapp.usecase.bases

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.domain.datasource.BasesDataSource
import com.example.vaseisapp.domain.datasource.DepartmentDataSource

class FetchBasesUseCase(private val dataSource: BasesDataSource){
    suspend operator fun invoke() {

    }
}