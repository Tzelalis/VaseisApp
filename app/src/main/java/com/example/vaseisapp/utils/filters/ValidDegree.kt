package com.example.vaseisapp.utils.filters

import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import java.util.regex.Pattern

class ValidDegree(private val minValue: Double, private val maxValue: Double) : InputFilter {

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        try {
            val input = (dest.toString() + source.toString())

            if(isInRightForm(input)){
                if(dest.equals('.'))
                    return dest

               /* if(!isInRightForm(input))
                    return ""*/

                if(!isInRange(minValue, maxValue, input.toDouble()) && input.toDouble()% 1.0 == 0.0){
                    return "."
                }



                return null
            }
        } catch (e: NumberFormatException) {
        }

        return ""
    }

    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c in a..b else c in b..a
    }

    private fun isInRightForm(input: String): Boolean {
        val p = Pattern.compile("[0-9]?[0-9]?(\\.[0-9][0-9]?)?")
        val m = p.matcher(input)
        return m.matches()
    }
}
