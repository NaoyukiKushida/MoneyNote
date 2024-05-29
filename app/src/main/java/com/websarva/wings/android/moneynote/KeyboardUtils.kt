package com.websarva.wings.android.moneynote

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

object KeyboardUtils {
    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setupKeyboardHideOnTouchOutside(activity: AppCompatActivity) {
        activity.window.decorView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    hideKeyboard(v)
                    v.clearFocus()
                }
            }
            false
        }
    }
}
