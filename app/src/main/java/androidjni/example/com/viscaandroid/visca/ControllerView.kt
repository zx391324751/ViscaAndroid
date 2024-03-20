package androidjni.example.com.viscaandroid.visca

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidjni.example.com.viscaandroid.controller.*
import androidjni.example.com.viscaandroid.utils.toPx
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * 2024/3/19
 * created by zhangxu
 */
class ControllerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val controllerRect = RectF()
    private val touchPoint = PointF()
    private val ballSize by lazy { 40f.toPx(context) }
    var controllerListener: ControllerListener? = null

    private val bgPaint by lazy {
        Paint().apply {
            this.color = Color.parseColor("#44000000")
            this.style = Paint.Style.FILL
        }
    }

    private val touchBallPaint by lazy {
        Paint().apply {
            this.color = Color.WHITE
            this.style = Paint.Style.FILL
        }
    }

    //强制设置正方形
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.controllerRect.left = 0 + ballSize / 2
        this.controllerRect.top = 0 + ballSize / 2
        this.controllerRect.right = w - ballSize / 2
        this.controllerRect.bottom = h - ballSize / 2
        updateTouchEvent(this.controllerRect.centerX(), this.controllerRect.centerY())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(controllerRect, controllerRect.width() / 2f, controllerRect.height() / 2f, bgPaint)
        canvas.drawCircle(touchPoint.x, touchPoint.y, ballSize / 2f, touchBallPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                updateTouchEvent(event.x, event.y)
                notifyControl(false)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                updateTouchEvent(event.x, event.y)
                notifyControl(false)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                updateTouchEvent(this@ControllerView.width / 2f, this@ControllerView.height / 2f)
                notifyControl(true)
            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateTouchEvent(x: Float, y: Float) {
        val preX = touchPoint.x
        val preY = touchPoint.y
        touchPoint.apply {
            this.x = x
            this.y = y
        }
        alignMoveInside(preX, preY)
        invalidate()
    }

    private fun alignMoveInside(preX: Float, preY: Float) {
        val distance = controllerRect.width() / 2f - ballSize / 2f
        val moveDistance = sqrt((touchPoint.x - controllerRect.centerX()).pow(2) + (touchPoint.y - controllerRect.centerY()).pow(2))
        if (moveDistance - distance > 0) {
            touchPoint.apply {
                val x0 = (this.x - controllerRect.centerX()) / moveDistance
                val y0 = (this.y - controllerRect.centerY()) / moveDistance
                this.x = controllerRect.centerX() + distance * x0
                this.y = controllerRect.centerY() + distance * y0
//                this.x = preX
//                this.y = preY
            }
        }
    }

    private fun notifyControl(isFinished: Boolean) {
        controllerListener?.onControl(getControllerDirector(), isFinished)
    }

    private fun getControllerDirector(): Int {
        val offset = ballSize / 2f
        val xDistance = abs(touchPoint.x - controllerRect.centerX())
        val yDistance = abs(touchPoint.y - controllerRect.centerY())
        val xValue = if (touchPoint.x > controllerRect.centerX() + offset) {
            CONTROLLER_RIGHT
        } else if (touchPoint.x < controllerRect.centerX() - offset) {
            CONTROLLER_LEFT
        } else {
            CONTROLLER_STOP
        }
        val yValue = if (touchPoint.y > controllerRect.centerY() + offset) {
            CONTROLLER_DOWN
        } else if (touchPoint.y < controllerRect.centerY() - offset) {
            CONTROLLER_UP
        } else {
            CONTROLLER_STOP
        }
        if (yDistance > xDistance * 2) {
            return yValue
        }
        if (xDistance > yDistance * 2)
            return xValue
        return xValue or yValue
    }

    interface ControllerListener {
        /**
         * @param director: 控制方向
         * @param isFinishControl: 是否结束控制
         */
        fun onControl(director: Int, isFinishControl: Boolean)
    }
}