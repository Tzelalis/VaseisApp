package com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails

import android.os.Handler
import android.util.Log
import android.view.animation.Interpolator
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import java.util.*

class AnimateDataSetChanged(
    private val duration: Int,
    private val chart: BarChart,
    private val fps: Int = 30,
    private var interpolator: Interpolator? = null
) : Runnable{
    private var startTime: Long = 0
    private val timerHandler: Handler by lazy { Handler() }
    private val oldData: MutableList<Entry> by lazy { mutableListOf() }
    private val newData: MutableList<Entry> by lazy { mutableListOf() }

    fun changeAnim(old: List<Entry>, new: List<Entry>) {
        startTime = Calendar.getInstance().timeInMillis

        oldData.clear()
        oldData.addAll(old)
        newData.clear()
        newData.addAll(new)
        run()
    }

    override fun run() {
        var increment = (Calendar.getInstance().timeInMillis - startTime) / duration.toFloat()
        interpolator?.let { increment = it.getInterpolation(if (increment < 0f) 0f else if (increment > 1f) 1f else increment) }

        Log.v("TZELPAOK", chart.data.getDataSetByIndex(0).toString())
        chart.data.getDataSetByIndex(0).clear()

        for (i in newData.indices) {
            val oldY = if (oldData.size > i) oldData[i].y else newData[i].y
            val oldX = if (oldData.size > i) oldData[i].x else newData[i].x
            val newX = newData[i].x
            val newY = newData[i].y
            val e = BarEntry(oldX + (newX - oldX) * increment, oldY + (newY - oldY) * increment)
            chart.data.addEntry(e, 0)
        }

        chart.xAxis.resetAxisMaximum()
        chart.xAxis.resetAxisMinimum()
        chart.refreshDrawableState()
        chart.invalidate()
        if (increment < 1f) {
            timerHandler.postDelayed(this, (1000 / fps).toLong())
        }
    }
}