package com.example.vaseisapp.di.modules

import com.example.vaseisapp.di.scopes.BaseAndroidApiHttpClient
import com.example.vaseisapp.di.scopes.BaseHttpClient
import com.example.vaseisapp.di.scopes.BasesHttpClient
import com.example.vaseisapp.utils.Utf8Interceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @BaseHttpClient
    @Singleton
    @Provides
    fun provideBaseClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Utf8Interceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        okHttpClient.addNetworkInterceptor(httpLoggingInterceptor)

        return okHttpClient.build()
    }

    @BaseAndroidApiHttpClient
    @ActivityScoped
    @Provides
    fun provideAndroidApiClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

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