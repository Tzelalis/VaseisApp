package com.vaseis.app.ui.dashboard.accountcenter.model

import android.os.Parcelable
import androidx.annotation.IntegerRes
import kotlinx.android.parcel.Parcelize

data class PropertiesFromPrefs(
    @IntegerRes val language: Int,
    @IntegerRes val theme : Int,
    val examsType: String,
    val fields: List<PrefProperty>
)

@Parcelize
data class PrefProperty(
    var id : String,
    var name : String
) : Parcelable