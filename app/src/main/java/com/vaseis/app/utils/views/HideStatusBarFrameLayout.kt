package com.vaseis.app.utils.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.FrameLayout

class HideStatusBarFrameLayout : FrameLayout {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun fitSystemWindows(insets: Rect): Boolean {
        insets.top = 0
        insets.bottom = 0
        return super.fitSystemWindows(insets)
    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        // executed by API >= 20.
        // removes the empty padding
        setPadding(0, 0, 0, insets.systemWindowInsetBottom)
        return insets.consumeSystemWindowInsets()
    }
}