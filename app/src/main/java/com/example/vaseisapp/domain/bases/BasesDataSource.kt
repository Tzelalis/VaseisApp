package com.example.vaseisapp.domain.bases

import com.example.vaseisapp.domain.bases.DepartmentBases

interface BasesDataSource{
    suspend fun getBases(code : String) : List<DepartmentBases>
}