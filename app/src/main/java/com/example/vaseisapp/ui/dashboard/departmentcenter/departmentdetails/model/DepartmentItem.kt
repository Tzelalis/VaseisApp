package com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model

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
