package com.example.vaseisapp.di.modules

import com.example.vaseisapp.data.university.UniversityDataSourceImpl
import com.example.vaseisapp.data.university.UniversityRepository
import com.example.vaseisapp.di.scopes.UniversitiesHttpClient
import com.example.vaseisapp.domain.university.UniversityDataSource
import com.example.vaseisapp.framework.network.UniversityApi
import com.example.vaseisapp.framework.repository.UniversityRepositoryImpl
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
object UniversitiesModule {

    @ActivityScoped // Instance only available on TicketsActivity
    @Provides
    fun provideTicketingRepository(ticketingApi: UniversityApi): UniversityRepository {
        return UniversityRepositoryImpl(ticketingApi)
    }

    @ActivityScoped
    @Provides
    fun provideTicketingDataSource(ticketingRepository: UniversityRepository): UniversityDataSource {
        return UniversityDataSourceImpl(ticketingRepository)
    }

    @ActivityScoped // Instance only available on TicketsActivity
    @Provides
    fun provideRetrofitForTicketing(@UniversitiesHttpClient okHttpClient: OkHttpClient, moshi: Moshi): UniversityApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/api/index.php/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(UniversityApi::class.java)
    }

    @UniversitiesHttpClient
    @ActivityScoped
    @Provides
    fun provideTicketingClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

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