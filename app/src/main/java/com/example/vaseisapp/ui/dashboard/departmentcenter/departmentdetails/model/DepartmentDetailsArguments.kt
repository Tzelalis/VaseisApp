package com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DepartmentDetailsArguments(
    val code : String,
    val name : String
) : Parcelable