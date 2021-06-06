package com.example.vaseisapp.di.modules

import com.example.vaseisapp.data.calculator.CalculatorDataSourceImpl
import com.example.vaseisapp.data.calculator.CalculatorRepository
import com.example.vaseisapp.di.scopes.BaseHttpClient
import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.framework.calculator.CalculatorApi
import com.example.vaseisapp.framework.calculator.CalculatorRepositoryImpl
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
object CalculatorModule {

    @Singleton
    @Provides
    fun providePropertiesApi(@BaseHttpClient okHttpClient: OkHttpClient, moshi: Moshi): CalculatorApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(CalculatorApi::class.java)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
interface CalculatorBindsModule {

    @Binds
    fun bindBasesDataSource(dataSource: CalculatorDataSourceImpl): CalculatorDataSource

    @Binds
    fun bindBasesRepository(repository: CalculatorRepositoryImpl): CalculatorRepository
}
