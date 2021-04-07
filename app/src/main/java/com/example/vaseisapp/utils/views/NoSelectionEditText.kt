package com.example.vaseisapp.utils.views

import android.content.Context
import android.util.AttributeSet

class NoSelectionEditText : androidx.appcompat.widget.AppCompatEditText {
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context) : super(context) {}

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)

        this.text?.length?.let { setSelection(it) }
    }
}