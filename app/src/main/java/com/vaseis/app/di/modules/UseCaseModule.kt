package com.vaseis.app.di.modules

import com.vaseis.app.domain.calculation.CalculatorDataSource
import com.vaseis.app.domain.prefs.PrefsDataSource
import com.vaseis.app.usecase.calculator.GetAllExamsTypesUseCase
import com.vaseis.app.usecase.calculator.SaveAllExamsTypesLocal
import com.vaseis.app.usecase.prefs.*
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
    fun provideSetGroupTypeUseCase(dataSource: PrefsDataSource): SetPrefsFieldsUseCase {
        return SetPrefsFieldsUseCase(dataSource)
    }

    @ActivityScoped
    @Provides
    fun provideGetGroupTypeUseCase(dataSource: PrefsDataSource): GetPrefsFieldsUseCase {
        return GetPrefsFieldsUseCase(dataSource)
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