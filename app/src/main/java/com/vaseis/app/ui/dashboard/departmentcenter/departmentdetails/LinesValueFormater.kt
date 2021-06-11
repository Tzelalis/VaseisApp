package com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.vaseis.app.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF


class CustomMarkerView(context: Context?, layoutResource: Int, private val backGroundColor : Int) : MarkerView(context, layoutResource) {
    private val marksTextView: TextView = findViewById<View>(R.id.marksTextView) as TextView
    private val marksCardView : CardView = findViewById<View>(R.id.marksCardView) as CardView

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        marksTextView.text = e?.y?.toInt().toString()
        marksCardView.setCardBackgroundColor(ResourcesCompat.getColor(context.resources, backGroundColor, null))
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width/2f), -height.toFloat())

        //return super.getOffset()
    }

/*    override fun getX(): Float {
        //return super.getX()
        return -(width / 2f)
    }


    override fun getY(): Float {
        return super.getY()
        //return -height.toFloat()
    }*/
}