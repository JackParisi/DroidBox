package com.github.giacomoparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.widget.ListPopupWindow
import android.widget.Spinner
import com.github.giacomoparisi.droidbox.utility.dpToPx

/**
 * Created by Giacomo Parisi on 21/08/17.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Set a fixed height for the view dropdown popup
 *
 * @param view The spinner view that need a custom dropdown popup height
 * @param height The height value in dp for the dropdown popup
 *
 */
@BindingAdapter("spinner_popupHeight")
fun bindSpinnerPopupHeight(view: Spinner, height: Float) {

    try {
        val popup = Spinner::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true

        val popupWindow = popup.get(view)

        if (popupWindow is ListPopupWindow) {
            popupWindow.height = dpToPx(height, view.context).toInt()
        }

    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}