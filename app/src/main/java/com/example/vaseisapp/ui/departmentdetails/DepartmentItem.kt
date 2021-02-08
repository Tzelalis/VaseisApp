package com.example.vaseisapp.ui.departmentdetails

import android.graphics.Color
import android.graphics.drawable.Drawable
import com.github.mikephil.charting.data.Entry

data class DepartmentItem(
    val deptCode: String,
    val deptName: String,
    val uniName: String,
    val uniShortName : String,
    var color : Int,
    var isBackgroundColorful : Boolean,
    val entries : List<Entry>
)