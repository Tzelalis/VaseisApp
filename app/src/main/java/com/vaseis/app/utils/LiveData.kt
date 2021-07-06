package com.vaseis.app.utils

object AuthenticationLiveData : SingleLiveEvent<Unit>()

object LoadingLiveData : SingleLiveEvent<Boolean>()

object ErrorLiveData : SingleLiveEvent<Int>()

object RestartLiveData : SingleLiveEvent<Unit>()