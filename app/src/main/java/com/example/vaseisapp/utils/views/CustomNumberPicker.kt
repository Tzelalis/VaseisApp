package com.example.vaseisapp.utils.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StyleableRes
import androidx.core.content.res.getResourceIdOrThrow
import com.example.vaseisapp.R
import com.example.vaseisapp.databinding.LayoutCustomNumberPickerBinding
import java.text.DecimalFormat
import java.util.*


class CustomNumberPicker(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    companion object {
        private val defMin: Double = 0.toDouble()
        private val defMax: Double = 20.toDouble()
    }

    var binding: LayoutCustomNumberPickerBinding = LayoutCustomNumberPickerBinding.inflate(LayoutInflater.from(context), this, true)

    @StyleableRes
    var index0 = 0

    @StyleableRes
    var index1 = 1

    @StyleableRes
    var index2 = 2

    @StyleableRes
    var index3 = 3


    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        inflate(context, R.layout.layout_custom_number_picker, this)
        val sets = intArrayOf(R.attr.leftIcon, R.attr.rightIcon, R.attr.minValue, R.attr.maxValue)
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, sets)
        val leftIcon = typedArray.getResourceIdOrThrow(index0)
        val rightIcon = typedArray.getResourceIdOrThrow(index1)
        val minValue = typedArray.getString(index2)
        val maxValue = typedArray.getString(index3)
        typedArray.recycle()

        val min = minValue?.toDouble() ?: defMin
        val max = maxValue?.toDouble() ?: defMax

        binding.lessonEdittext.apply {
            setOnFocusChangeListener { _, hasFocus ->
                if(hasFocus)
                    binding.root.setBackgroundResource(R.drawable.shape_calculator_edittext_selected)
                else
                    binding.root.setBackgroundResource(R.drawable.shape_calculator_edittext_not_selected)
            }
        }

        try {
            binding.decreaseButton.setImageResource(leftIcon)
        } catch (ex: Exception) {
        }


        binding.decreaseButton.apply {
            try {
                binding.increaseButton.setImageResource(rightIcon)
            } catch (ex: Exception) {
            }

            setOnClickListener {
                binding.lessonEdittext.requestFocus()

                try {
                    val degree = binding.lessonEdittext.text.toString()
                    if(degree == ""){
                        setLessonText(0.0)
                        return@setOnClickListener
                    }
                    val newDegree = degree.toDouble() - 1
                    if (newDegree in min..max)
                        setLessonText(newDegree)
                    else
                        setLessonText(0.0)



                } catch (e: NumberFormatException) {
                }
            }
        }


        binding.increaseButton.apply {
            try {
                setImageResource(rightIcon)
            } catch (ex: Exception) {}

            setOnClickListener {
                binding.lessonEdittext.requestFocus()

                try {
                    val degree = binding.lessonEdittext.text.toString()
                    if (degree == "") {
                        setLessonText(1.0)
                        return@setOnClickListener
                    }

                    val newDegree = degree.toDouble() + 1
                    if (newDegree in min..max)
                        setLessonText(newDegree)
                    else
                        setLessonText(max)

                } catch (e: NumberFormatException) {
                }
            }
        }
    }

    private fun setLessonText(degree : Double)  {
        if(degree%1.0 == 0.0)
            binding.lessonEdittext.setText(String.format(Locale.ENGLISH,"%.0f", degree))
        else
            binding.lessonEdittext.setText(String.format(Locale.ENGLISH,"%.2f", degree))

    }
}