package com.example.vaseisapp.di.modules

import com.example.vaseisapp.di.scopes.BaseHttpClient
import com.example.vaseisapp.utils.Utf8Interceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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


/*    @Singleton
    @Provides
    fun provideRetrofitForUser(@BaseHttpClient okHttpClient: OkHttpClient, moshi: Moshi): UniversityApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/api/index.php/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(UniversityApi::class.java)
    }*/


}