package com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Utils {

    @JvmName("KeyboardUtils")
    fun hideSoftKeyboard(context: Context, view: View) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager!!.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
