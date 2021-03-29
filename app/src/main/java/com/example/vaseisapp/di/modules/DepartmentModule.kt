package com.example.vaseisapp.di.modules

import com.example.vaseisapp.data.department.DepartmentDataSourceImpl
import com.example.vaseisapp.data.department.DepartmentRepository
import com.example.vaseisapp.di.scopes.DepartmentHttpClient
import com.example.vaseisapp.domain.department.DepartmentDataSource
import com.example.vaseisapp.framework.network.DepartmentApi
import com.example.vaseisapp.framework.repository.DepartmentRepositoryImpl
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
object DepartmentModule {
    @ActivityScoped // Instance only available on TicketsActivity
    @Provides
    fun provideDepartmentRepository(api: DepartmentApi): DepartmentRepository {
        return DepartmentRepositoryImpl(api)
    }

    @ActivityScoped
    @Provides
    fun provideDepartmentDataSource(repository: DepartmentRepository): DepartmentDataSource {
        return DepartmentDataSourceImpl(repository)
    }

    @ActivityScoped // Instance only available on TicketsActivity
    @Provides
    fun provideRetrofitForDepartment(@DepartmentHttpClient okHttpClient: OkHttpClient, moshi: Moshi): DepartmentApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/api/index.php/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(DepartmentApi::class.java)
    }

    @DepartmentHttpClient
    @ActivityScoped
    @Provides
    fun provideDepartmentClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

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