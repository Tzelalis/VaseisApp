package com.example.vaseisapp.di.modules

import android.content.SharedPreferences
import com.example.vaseisapp.data.prefs.PrefsDataSourceImpl
import com.example.vaseisapp.data.prefs.PrefsRepository
import com.example.vaseisapp.domain.prefs.PrefsDataSource
import com.example.vaseisapp.framework.prefs.PrefsRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object PrefsModule {

    @ActivityScoped
    @Provides
    fun providePrefsRepository(prefs: SharedPreferences): PrefsRepository {
        return PrefsRepositoryImpl(prefs)
    }

    @ActivityScoped
    @Provides
    fun providePrefsDataSource(repository: PrefsRepository, moshi: Moshi): PrefsDataSource {
        return PrefsDataSourceImpl(repository, moshi)
    }
}