package com.example.vaseisapp.di.modules

import android.content.SharedPreferences
import com.example.vaseisapp.data.properties.PropertiesDataSourceImpl
import com.example.vaseisapp.data.properties.PropertiesRepository
import com.example.vaseisapp.di.scopes.BaseHttpClient
import com.example.vaseisapp.domain.properties.PropertiesDataSource
import com.example.vaseisapp.framework.properties.PropertiesApi
import com.example.vaseisapp.framework.properties.PropertiesRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object PropertiesModule {

    @Singleton
    @Provides
    fun providePropertiesApi(@BaseHttpClient okHttpClient: OkHttpClient, moshi: Moshi): PropertiesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(PropertiesApi::class.java)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
interface PropertiesBindsModule {

    @Binds
    fun bindPropertiesDataSource(dataSource: PropertiesDataSourceImpl): PropertiesDataSource

    @Binds
    fun bindPropertiesRepository(repository: PropertiesRepositoryImpl): PropertiesRepository
}