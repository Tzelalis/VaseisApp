package com.example.vaseisapp.ui.departmentdetails

import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseViewModel
import com.github.mikephil.charting.data.Entry

class DepartmentDetailsViewModel : BaseViewModel() {
    private var _departmentItems = MutableLiveData<List<DepartmentItem>>()
    val departmentItem = _departmentItems

    fun loadSelectedDepartments() {
        var result = mutableListOf<DepartmentItem>()

        val entries1 = mutableListOf<Entry>()
        entries1.add(Entry(2010f, 13600f))
        entries1.add(Entry(2011f, 13000f))
        entries1.add(Entry(2012f, 14000f))
        entries1.add(Entry(2013f, 12400f))
        entries1.add(Entry(2014f, 13000f))
        entries1.add(Entry(2015f, 15000f))
        entries1.add(Entry(2016f, 13600f))
        entries1.add(Entry(2017f, 13000f))
        entries1.add(Entry(2018f, 14000f))
        entries1.add(Entry(2019f, 12400f))
        entries1.add(Entry(2020f, 13000f))
        entries1.add(Entry(2021f, 15000f))

        val entries2 = mutableListOf<Entry>()
        entries2.add(Entry(2010f, 16600f))
        entries2.add(Entry(2011f, 17000f))
        entries2.add(Entry(2012f, 16400f))
        entries2.add(Entry(2013f, 18400f))
        entries2.add(Entry(2014f, 16000f))
        entries2.add(Entry(2015f, 16000f))
        entries2.add(Entry(2016f, 15600f))
        entries2.add(Entry(2017f, 15167f))
        entries2.add(Entry(2018f, 18010f))
        entries2.add(Entry(2019f, 17404f))
        entries2.add(Entry(2020f, 16006f))
        entries2.add(Entry(2021f, 15044f))

        val entries3 = mutableListOf<Entry>()
        entries3.add(Entry(2010f, 14600f))
        entries3.add(Entry(2011f, 16000f))
        entries3.add(Entry(2012f, 14400f))
        entries3.add(Entry(2013f, 13400f))
        entries3.add(Entry(2014f, 16000f))
        entries3.add(Entry(2015f, 14000f))
        entries3.add(Entry(2016f, 15600f))
        entries3.add(Entry(2017f, 13167f))
        entries3.add(Entry(2018f, 15010f))
        entries3.add(Entry(2019f, 14404f))
        entries3.add(Entry(2020f, 15006f))
        entries3.add(Entry(2021f, 15044f))

        val entries4 = mutableListOf<Entry>()
        entries4.add(Entry(2010f, 04600f))
        entries4.add(Entry(2011f, 06000f))
        entries4.add(Entry(2012f, 04400f))
        entries4.add(Entry(2013f, 07400f))
        entries4.add(Entry(2014f, 06000f))
        entries4.add(Entry(2015f, 04000f))
        entries4.add(Entry(2016f, 05600f))
        entries4.add(Entry(2017f, 07167f))
        entries4.add(Entry(2018f, 05010f))
        entries4.add(Entry(2019f, 04404f))
        entries4.add(Entry(2020f, 05006f))
        entries4.add(Entry(2021f, 05044f))


        val dummy = listOf(
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΔΙΠΑΕ", R.color.lineColor1, false, entries1),
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΔΙΠΑΕ", R.color.lineColor2, false, entries2),
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΔΙΠΑΕ", R.color.lineColor3, false, entries3),
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΑΠΘ", R.color.lineColor4, false, entries4),
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΔΙΠΑΕ", R.color.lineColor1, false, entries1),
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΔΙΠΑΕ", R.color.lineColor2, false, entries2),
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΔΙΠΑΕ", R.color.lineColor3, false, entries3),
            DepartmentItem("444", "ΜΗΧΑΝΙΚΩΝ ΠΛΗΡΟΦΟΡΙΚΗΣ ΚΑΙ ΗΛΕΚΤΡΟΝΙΚΩΝ ΣΥΣΤΗΜΑΤΩΝ", "", "ΑΠΘ", R.color.lineColor4, false, entries4),
        )

        dummy[0].isBackgroundColorful = true

        _departmentItems.value = dummy
    }

    fun departmentPopularity(){
        //todo return a double about popularity of department. Calculate with positions (firstPos*3 + secondPos*2...etc)
    }

}