package com.chauhan.todo.utils

// DraggableFloatingActionButton.kt
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DraggableFloatingActionButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FloatingActionButton(context, attrs, defStyleAttr) {

    private var dX: Float = 0f
    private var dY: Float = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val rawX = event.rawX
        val rawY = event.rawY
        val layoutParams = this.layoutParams as CoordinatorLayout.LayoutParams
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("FloatingButton", "Action down at X: $x, Y: $y")
                dX = x - event.rawX
                dY = y - event.rawY
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("FloatingButton", "Action move to X: $x, Y: $y")
                val newX = rawX + dX
                val newY = rawY + dY
                if (newX < 0) {
                    layoutParams.leftMargin = newX.toInt()
                } else {
                    layoutParams.leftMargin = newX.toInt()
                }
                if (newY < 0) {
                    layoutParams.topMargin = newY.toInt()
                } else {
                    layoutParams.topMargin = newY.toInt()
                }
                layoutParams.rightMargin = 0
                layoutParams.bottomMargin = 0
                this.layoutParams = layoutParams
                return true
            }
            MotionEvent.ACTION_UP -> {
                Log.d("FloatingButton", "Action up at X: $x, Y: $y")
                performClick() // Handle click event
                return true
            }
            else -> return super.onTouchEvent(event)
        }
    }

    override fun performClick(): Boolean {
        super.performClick()
        // Handle click event here if needed
        return true
    }
}
