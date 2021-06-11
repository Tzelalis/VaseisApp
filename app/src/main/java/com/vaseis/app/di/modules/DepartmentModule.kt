package com.vaseis.app.di.modules

import com.vaseis.app.data.department.DepartmentDataSourceImpl
import com.vaseis.app.data.department.DepartmentRepository
import com.vaseis.app.di.scopes.BaseHttpClient
import com.vaseis.app.domain.department.DepartmentDataSource
import com.vaseis.app.framework.network.DepartmentApi
import com.vaseis.app.framework.repository.DepartmentRepositoryImpl
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
object BasesModule {

    @Singleton
    @Provides
    fun providePropertiesApi(@BaseHttpClient okHttpClient: OkHttpClient, moshi: Moshi): DepartmentApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(DepartmentApi::class.java)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
interface BasesBindsModule {

    @Binds
    fun bindBasesDataSource(dataSource: DepartmentDataSourceImpl): DepartmentDataSource

    @Binds
    fun bindBasesRepository(repository: DepartmentRepositoryImpl): DepartmentRepository
}

