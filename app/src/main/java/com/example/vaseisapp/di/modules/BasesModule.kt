package com.example.vaseisapp.di.modules

import com.example.vaseisapp.data.bases.BasesDataSourceImpl
import com.example.vaseisapp.data.bases.BasesRepository
import com.example.vaseisapp.data.properties.PropertiesDataSourceImpl
import com.example.vaseisapp.data.properties.PropertiesRepository
import com.example.vaseisapp.di.scopes.BaseHttpClient
import com.example.vaseisapp.di.scopes.BasesHttpClient
import com.example.vaseisapp.domain.bases.BasesDataSource
import com.example.vaseisapp.domain.properties.PropertiesDataSource
import com.example.vaseisapp.framework.network.BasesApi
import com.example.vaseisapp.framework.properties.PropertiesApi
import com.example.vaseisapp.framework.properties.PropertiesRepositoryImpl
import com.example.vaseisapp.framework.repository.BasesRepositoryImpl
import com.example.vaseisapp.utils.Utf8Interceptor
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
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