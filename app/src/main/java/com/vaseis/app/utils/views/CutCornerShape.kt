package com.vaseis.app.utils.views

import android.graphics.*
import android.graphics.drawable.shapes.Shape

class CutCornerShape() : Shape() {
    private val border = Paint()
    private val path: Path = Path()
    override fun onResize(width: Float, height: Float) {
        super.onResize(width, height)
        val dx = STROKE_WIDTH / 2.0f
        val dy = STROKE_WIDTH / 2.0f
        val w = width - dx
        val h = height - dy
        val arc = RectF(dx, h, dx, h)
        path.reset()
        path.moveTo(dx + CORNER, dy)
        path.lineTo(w - CORNER, dy)
        path.lineTo(w, dy + CORNER)
        path.lineTo(w, h)
        path.lineTo(dx + CORNER, h)
        path.arcTo(arc, 90.0f, 90.0f)
        path.lineTo(dx, dy)
        path.close()
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, border)
    }

    companion object {
        private val COLOUR = Color.parseColor("#5D8BB1")
        private const val STROKE_WIDTH = 1.0f
        private const val CORNER = 16.0f
    }

    init {
        border.color = COLOUR
        border.style = Paint.Style.FILL_AND_STROKE
        border.strokeWidth = STROKE_WIDTH
        border.isAntiAlias = true
        border.isDither = true
        border.strokeJoin = Paint.Join.ROUND
        border.strokeCap = Paint.Cap.ROUND
    }
}