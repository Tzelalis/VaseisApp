package com.vaseis.app.di.scopes

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

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasesHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PropertiesHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseAndroidApiHttpClient