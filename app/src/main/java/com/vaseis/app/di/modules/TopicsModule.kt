package com.vaseis.app.di.modules

import com.vaseis.app.data.topics.TopicsDataSourceImpl
import com.vaseis.app.data.topics.TopicsRepository
import com.vaseis.app.di.scopes.BaseHttpClient
import com.vaseis.app.domain.topics.TopicsDataSource
import com.vaseis.app.framework.topics.TopicsApi
import com.vaseis.app.framework.topics.TopicsRepositoryImpl
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
object TopicsModule {

    @Singleton
    @Provides
    fun providePropertiesApi(@BaseHttpClient okHttpClient: OkHttpClient, moshi: Moshi): TopicsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vaseis.iee.ihu.gr/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(TopicsApi::class.java)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
interface TopicsBindsModule {

    @Binds
    fun bindTopicsDataSource(dataSource: TopicsDataSourceImpl): TopicsDataSource

    @Binds
    fun bindTopicsRepository(repository: TopicsRepositoryImpl): TopicsRepository
}
