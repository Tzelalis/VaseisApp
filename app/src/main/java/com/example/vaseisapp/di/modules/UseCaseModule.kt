package com.example.vaseisapp.di.modules

import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.domain.prefs.PrefsDataSource
import com.example.vaseisapp.usecase.calculator.GetAllExamsTypesUseCase
import com.example.vaseisapp.usecase.calculator.SaveAllExamsTypesLocal
import com.example.vaseisapp.usecase.prefs.*
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
    fun provideSaveAllExamsTypesLocalUseCase(dataSource: CalculatorDataSource): SaveAllExamsTypesLocal {
        return SaveAllExamsTypesLocal(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideGetAllExamsTypesUseCase(dataSource: CalculatorDataSource): GetAllExamsTypesUseCase {
        return GetAllExamsTypesUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideSetLanguageUseCase(dataSource: PrefsDataSource): SetLanguageUseCase {
        return SetLanguageUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideGetLanguageUseCase(dataSource: PrefsDataSource): GetLanguageUseCase {
        return GetLanguageUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideSetExamTypeUseCase(dataSource: PrefsDataSource): SetExamTypeUseCase {
        return SetExamTypeUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideGetExamTypeUseCase(dataSource: PrefsDataSource): GetExamTypeUseCase {
        return GetExamTypeUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideSetGroupTypeUseCase(dataSource: PrefsDataSource): SetGroupTypeUseCase {
        return SetGroupTypeUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideGetGroupTypeUseCase(dataSource: PrefsDataSource): GetGroupTypeUseCase {
        return GetGroupTypeUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideSetThemeUseCase(dataSource: PrefsDataSource): SetThemeUseCase {
        return SetThemeUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideGetThemeUseCase(dataSource: PrefsDataSource): GetThemeUseCase {
        return GetThemeUseCase(dataSource)
    }
}