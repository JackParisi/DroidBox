package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.widget.ListPopupWindow
import android.widget.Spinner
import com.github.jackparisi.droidbox.utility.dpToPx

/**
 * Created by Giacomo Parisi on 21/08/17.
 * https://github.com/JackParisi
 */

@BindingAdapter("popupHeight")
fun bindSpinnerPopupHeight(spinner: Spinner, height: Float) {

    try {
        val popup = Spinner::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true

        val popupWindow = popup.get(spinner)

        if (popupWindow is ListPopupWindow) {
            popupWindow.height = dpToPx(height, spinner.context).toInt()
        }

    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}