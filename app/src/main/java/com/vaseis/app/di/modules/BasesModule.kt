package com.vaseis.app.di.modules

import com.vaseis.app.data.bases.BasesDataSourceImpl
import com.vaseis.app.data.bases.BasesRepository
import com.vaseis.app.di.scopes.BaseHttpClient
import com.vaseis.app.domain.bases.BasesDataSource
import com.vaseis.app.framework.network.BasesApi
import com.vaseis.app.framework.repository.BasesRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object BasesTestModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    object PropertiesModule {

        @Singleton
        @Provides
        fun providePropertiesApi(@BaseHttpClient okHttpClient: OkHttpClient, moshi: Moshi): BasesApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://vaseis.iee.ihu.gr/api/index.php/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create(BasesApi::class.java)
        }
    }

    @Module
    @InstallIn(ApplicationComponent::class)
    interface BasesBindsModule {

        @Binds
        fun bindBasesDataSource(dataSource: BasesDataSourceImpl): BasesDataSource

        @Binds
        fun bindBasesRepository(repository: BasesRepositoryImpl): BasesRepository
    }
}