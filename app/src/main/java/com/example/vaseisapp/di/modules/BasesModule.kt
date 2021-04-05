package com.example.vaseisapp.di.modules

import com.example.vaseisapp.data.bases.BasesDataSourceImpl
import com.example.vaseisapp.data.bases.BasesRepository
import com.example.vaseisapp.di.scopes.BasesHttpClient
import com.example.vaseisapp.domain.bases.BasesDataSource
import com.example.vaseisapp.framework.network.BasesApi
import com.example.vaseisapp.framework.repository.BasesRepositoryImpl
import com.example.vaseisapp.utils.Utf8Interceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
object BasesModule {

    @ActivityScoped
    @Provides
    fun provideBasesRepository(api: BasesApi): BasesRepository {
        return BasesRepositoryImpl(api)
    }

    @ActivityScoped
    @Provides
    fun provideBasesDataSource(repository: BasesRepository): BasesDataSource {
        return BasesDataSourceImpl(repository)
    }

    @ActivityScoped
    @Provides
    fun provideRetrofitForBases(@BasesHttpClient okHttpClient: OkHttpClient, moshi: Moshi): BasesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/api/index.php/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(BasesApi::class.java)
    }

    @BasesHttpClient
    @ActivityScoped
    @Provides
    fun provideBasesClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .build()
                it.proceed(request)
            }
            .addInterceptor(Utf8Interceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        okHttpClient.addNetworkInterceptor(httpLoggingInterceptor)

        return okHttpClient.build()
    }
}