package com.github.salonkasoli.moneytracker.ui.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.github.salonkasoli.moneytracker.util.PxUtils

class CircleChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val dividerSize: Float = 2f

    private val segmentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val archPadding: Float

    private var segments: List<Segment> = emptyList()

    init {
        val strokeWidth = PxUtils.dpToPx(20f)
        segmentPaint.strokeWidth = strokeWidth
        archPadding = strokeWidth / 2

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        if (segments.isEmpty()) {
            return
        }

        val dividersTotalSize = dividerSize  * segments.size
        val sweep = (360f - dividersTotalSize) / segments.size

        var currentAngle = 0f
        segments.forEach {
            segmentPaint.color = it.colorInt
            canvas.drawArc(
                0f + archPadding,
                0f + archPadding,
                measuredWidth.toFloat() - archPadding,
                measuredHeight.toFloat() - archPadding,
                currentAngle,
                sweep,
                false,
                segmentPaint
            )
            currentAngle += (sweep + dividerSize)
        }
    }

    fun setSegments(segments: List<Segment>) {
        this.segments = segments
        invalidate()
    }
}

class Segment(
    @ColorInt
    val colorInt: Int
)