package com.example.vaseisapp.di.modules

import com.example.vaseisapp.domain.datasource.DepartmentDataSource
import com.example.vaseisapp.domain.datasource.UniversityDataSource
import com.example.vaseisapp.usecase.department.FetchAllDepartmentsUseCase
import com.example.vaseisapp.usecase.university.FetchAllUniversitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @ActivityScoped
    @Provides
    fun provideFetchAllUniversitiesUseCase(dataSource : UniversityDataSource): FetchAllUniversitiesUseCase {
        return FetchAllUniversitiesUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideFetchAllDepartmentsUseCase(dataSource : DepartmentDataSource): FetchAllDepartmentsUseCase {
        return FetchAllDepartmentsUseCase(dataSource)
    }
}