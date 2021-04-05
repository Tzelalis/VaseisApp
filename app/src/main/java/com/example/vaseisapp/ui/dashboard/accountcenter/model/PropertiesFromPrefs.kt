package com.example.vaseisapp.ui.dashboard.accountcenter.model

import android.os.Parcelable
import androidx.annotation.IntegerRes
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class PropertiesFromPrefs(
    @IntegerRes val language: Int,
    val examsType: String,
    val groupType: String
)

@Parcelize
data class PrefProperty(
    var id : String,
    var name : String
) : Parcelable