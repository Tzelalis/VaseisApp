package com.vaseis.app.utils.filters

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class MinMaxFilter(private val minValue: Double, private val maxValue: Double) : InputFilter {
    //filter works properly only with NoSelectionEditText
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        try {
            var input = ""
            if(dest.isEmpty())
                input = source.toString()
            else if(dstart == 0)
                input = source.toString() + dest.toString()
            else if(dstart == dest.length)
                input = dest.toString() + source.toString()


            if(input == "")
                return null

            if (isInRange(minValue, maxValue, input.toDouble())) {
                return null
            }

            if (!isInRange(minValue, maxValue, input.toDouble()) && input.toDouble() % 1.0 == 0.0 && source.isNotEmpty()) {
                return "."
            }

        } catch (e: java.lang.NumberFormatException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}

class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) : InputFilter {
    private val mPattern: Pattern = Pattern.compile("[0-9]{0,$digitsBeforeZero}+((\\.[0-9]{0,${digitsAfterZero - 1}})?)||(\\.)?")
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        val matcher = mPattern.matcher(dest)
        return if (!matcher.matches()) "" else null
    }
}

/*class ValidDegree(private val minValue: Double, private val maxValue: Double) : InputFilter {

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        try {
            val input = ( source.toString() + dest.toString())

            Log.v("Tzel", input)

            //if (isInRightForm(input))
                return dest



                 if(!isInRightForm(input))
                     return ""

                if (!isInRange(minValue, maxValue, input.toDouble()) && input.toDouble() % 1.0 == 0.0) {
                    return "."
                }

                return null

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
}*/