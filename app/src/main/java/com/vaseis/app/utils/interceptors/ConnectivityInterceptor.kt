package com.vaseis.app.utils.interceptors

import com.vaseis.app.R
import com.vaseis.app.utils.ErrorLiveData
import com.vaseis.app.utils.helper.ConnectivityHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NoInternetException : IOException()

class ConnectivityInterceptor @Inject constructor(
    private val connectivityHelper: ConnectivityHelper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityHelper.checkInternetConnection()) {
            ErrorLiveData.postValue(R.string.error_no_internet)
            throw NoInternetException()
        }
        return chain.proceed(chain.request())
    }
}