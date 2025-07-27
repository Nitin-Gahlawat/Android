package com.example.buttongraphics

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch





class MyCanvas(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var isOn = false

    private val outerArcStyle: Paint = Paint().apply {
        this.color = Color.RED
        this.style = Paint.Style.STROKE
        this.isAntiAlias = true
        this.strokeWidth = 40f
        this.strokeCap = Paint.Cap.ROUND
    }

    private var outerArcProgress: Float = 270f

    var flickerCircleProgress = 0f

    var lineProgress: Float = 150f
    var lineStyle = Paint().apply {
        this.style = Paint.Style.FILL
        this.color = Color.RED
        this.strokeWidth = 40f
        this.strokeCap = Paint.Cap.ROUND
    }

    inner class Remove {
        suspend fun removeArc() {
            for (i in 270 downTo 0 step 5) {
                delay(10)

                outerArcProgress = i.toFloat()
                invalidate()
            }
            outerArcStyle.color = Color.LTGRAY

        }

        suspend fun removeLine() {
            for (i in 150 downTo 1 step 5) {
                delay(10)
                lineProgress = i.toFloat()
                invalidate()
            }
            lineStyle.color = Color.LTGRAY
        }


        fun showFlicker() {
            val customScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

            customScope.launch {
                for (i in 1..100) {
                    flickerCircleProgress = i.toFloat()
                    invalidate()
                    delay(50)
                }
            }

        }
    }

    private suspend fun removePresent() {
        val x = Remove()
        x.removeArc()
        if (lineStyle.color == Color.RED) x.showFlicker()
        x.removeLine()
        delay(300)
    }


    private suspend fun addNew(progressColor: Int) {
        lineStyle.color = progressColor
        for (i in 1 until 150 step 5) {
            delay(20)
            lineProgress = i.toFloat()
            invalidate()
        }
        outerArcStyle.color = progressColor
        for (i in 0..270 step 5) {
            delay(20)
            outerArcProgress = i.toFloat()
            invalidate()
        }


    }


    private var paint = Paint().apply {
        this.color = Color.WHITE
        this.style = Paint.Style.FILL
        this.isAntiAlias = true
    }

    private fun drawCircle(
        centerX: Float, centerY: Float, progress: Float, canvas: Canvas, dimen: Float
    ) {
        val maxProgress = 100f
        val radius = ((progress / maxProgress) * ((height / 2.3) + dimen)).toFloat()
        val alpha = ((255 - (((progress) / maxProgress) * 255).toInt()))
        paint.alpha = if (alpha < 0) 0 else alpha
        canvas.drawCircle(
            centerX, centerY, radius, paint
        )
    }

    suspend fun turnOn(
    ) {

        removePresent()
        addNew(Color.GREEN)
        isOn = true

    }

    suspend fun turnOff(

    ) {
        removePresent()
        addNew(Color.RED)
        isOn = false
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val centerX: Float = (width / 2.0f)
        val centerY: Float = (height / 2.0f)


//        Adding Gradient background
        canvas.drawPaint(Paint().apply {
            val gradient = LinearGradient(
                0f,
                200f,
                0f,
                500f,
                Color.parseColor("#B3FF20"),
                Color.parseColor("#1DC87C"),
                Shader.TileMode.CLAMP
            )
            this.setShader(gradient)
        })

//Adding Flicker circle
        drawCircle(centerX, centerY, progress = flickerCircleProgress + 5f, canvas, dimen = 20f)
        drawCircle(centerX, centerY, progress = flickerCircleProgress + 20f, canvas, dimen = 10f)
        drawCircle(centerX, centerY, progress = flickerCircleProgress + 35f, canvas, dimen = 0f)

//Making the make button background
        canvas.drawCircle(centerX, centerY, 280f, Paint().apply {

            this.style = Paint.Style.FILL
            color = Color.WHITE
            this.setShadowLayer(10f, 5f, 5f, Color.LTGRAY)
        })

//        Add stroke to the main button
        canvas.drawCircle(centerX, centerY, 280f, Paint().apply {
            this.style = Paint.Style.STROKE
            color = Color.parseColor("#83CA43")
            strokeWidth = 30f
        })

//Drawing the middle line
        canvas.drawLine(centerX, centerY - 150f, centerX, centerY + 50f, Paint().apply {
            this.style = Paint.Style.FILL
            this.color = Color.LTGRAY
            this.strokeWidth = 40f
            this.strokeCap = Paint.Cap.ROUND
        })

//        Making thr background line
        canvas.drawLine(centerX, centerY - lineProgress, centerX, centerY + 50f, lineStyle)

//        making the background arc
        canvas.drawArc(RectF(centerX - 150f, centerY - 150f, centerX + 150f, centerY + 150f),
            -45f,
            270f,
            false,
            Paint().apply {
                this.style = Paint.Style.STROKE
                this.color = Color.LTGRAY
                this.strokeWidth = 40f
                this.strokeCap = Paint.Cap.ROUND
            })
//        Making the actual arc
        canvas.drawArc(
            RectF(centerX - 150f, centerY - 150f, centerX + 150f, centerY + 150f),
            -45f,
            outerArcProgress,
            false,
            outerArcStyle
        )
    }


}