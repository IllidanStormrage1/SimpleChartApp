package ru.zudov.simplechartview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.properties.Delegates

class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val columnPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
    }
    private val axisPaint = Paint().apply {
        isAntiAlias = false
        style = Paint.Style.STROKE
    }
    private val xyAxisPaint = Paint().apply {
        isAntiAlias = false
        style = Paint.Style.STROKE
    }

    private var values: MutableList<Double> = mutableListOf()
    private var columnSpacing: Float by Delegates.notNull()
    private var columnCornerRadius: Float by Delegates.notNull()

    fun add(value: Double) = values.add(value)

    fun addAll(collection: Collection<Double>) {
        values.clear()
        values.addAll(collection)
    }

    init {
        initView(context, attrs, defStyleAttr)
    }

    private fun initView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) =
        context.withStyledAttributes(attrs, R.styleable.ChartView, defStyleAttr) {
            columnPaint.color =
                getColor(R.styleable.ChartView_columnColor, R.attr.colorPrimary)
            axisPaint.color =
                getColor(R.styleable.ChartView_axisColor, Color.TRANSPARENT)
            xyAxisPaint.color =
                getColor(R.styleable.ChartView_axisColorXY, Color.TRANSPARENT)
            columnSpacing = getDimension(R.styleable.ChartView_itemSpacing, 0f)
            columnCornerRadius = getDimension(R.styleable.ChartView_columnCornerRadius, 0f)
            xyAxisPaint.strokeWidth = getDimension(R.styleable.ChartView_axisXYWidthStroke, 2f)
        }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        with(canvas) {
            val viewportWidth = width - paddingStart - paddingEnd.toFloat()
            val viewportHeight = height - paddingTop - paddingBottom.toFloat()
            val itemWidth = viewportWidth / values.size
            val maxItemHeight = values.max() ?: 0.0

            if (axisPaint.color != Color.TRANSPARENT)
                values.forEach {
                    drawLine(
                        paddingStart.toFloat(),
                        (height - paddingBottom - viewportHeight / maxItemHeight * it).toFloat(),
                        paddingEnd + viewportWidth,
                        (height - paddingBottom - viewportHeight / maxItemHeight * it).toFloat(),
                        axisPaint
                    )
                }
            values.forEachIndexed { i, it ->
                drawRoundRect(
                    paddingStart + i * itemWidth + columnSpacing,
                    (height - paddingBottom - viewportHeight / maxItemHeight * it).toFloat(),
                    paddingStart + (i + 1) * itemWidth - columnSpacing,
                    height - paddingBottom.toFloat(),
                    columnCornerRadius/2,
                    columnCornerRadius/2,
                    columnPaint
                )
                drawRect(
                    paddingStart + i * itemWidth + columnSpacing,
                    (height - paddingBottom - viewportHeight / maxItemHeight * it).toFloat() + columnCornerRadius,
                    paddingStart + (i + 1) * itemWidth - columnSpacing,
                    height - paddingBottom.toFloat(),
                    columnPaint
                )
            }
            if (xyAxisPaint.color != Color.TRANSPARENT) {
                drawLine(
                    paddingStart.toFloat(),
                    paddingTop.toFloat(),
                    paddingStart.toFloat(),
                    viewportHeight + paddingBottom,
                    xyAxisPaint
                )
                drawLine(
                    paddingStart.toFloat(),
                    viewportHeight + paddingBottom,
                    viewportWidth,
                    viewportHeight + paddingBottom,
                    xyAxisPaint
                )
            }
        }
        super.onDraw(canvas)
    }
}