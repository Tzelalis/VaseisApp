package com.example.vaseisapp.di.modules

import android.content.SharedPreferences
import com.example.vaseisapp.data.calculator.CalculatorDataSourceImpl
import com.example.vaseisapp.data.calculator.CalculatorRepository
import com.example.vaseisapp.di.scopes.BasesHttpClient
import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.framework.calculator.CalculatorApi
import com.example.vaseisapp.framework.calculator.CalculatorRepositoryImpl
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object CalculatorModule {

    @ActivityScoped
    @Provides
    fun provideCalculatorRepository(api: CalculatorApi, prefs: SharedPreferences, moshi: Moshi): CalculatorRepository {
        return CalculatorRepositoryImpl(api, prefs, moshi)
    }

    @ActivityScoped
    @Provides
    fun provideCalculatorDataSource(repository: CalculatorRepository): CalculatorDataSource {
        return CalculatorDataSourceImpl(repository)
    }


    @ActivityScoped
    @Provides
    fun provideRetrofitForCalculator(@BasesHttpClient okHttpClient: OkHttpClient, moshi: Moshi): CalculatorApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/api/index.php/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(CalculatorApi::class.java)
    }
}