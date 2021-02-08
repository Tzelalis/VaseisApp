package com.example.vaseisapp.di.scopes

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseHttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UniversitiesHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DepartmentHttpClient