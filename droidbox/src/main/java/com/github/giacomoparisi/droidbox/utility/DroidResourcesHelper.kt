package com.github.giacomoparisi.droidbox.utility

import androidx.annotation.StringRes
import android.view.View

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
object DroidResourcesHelper {

    fun getStringOrDefault(view: View,
                           string: String?,
                           @StringRes defaultMessage: Int): String =
            if (string.isNullOrEmpty())
                view.context.getString(defaultMessage)
            else string!!

    fun getStringOrDefault(view: View,
                           string: String?,
                           @StringRes defaultMessage: Int,
                           value: Int): String =
            if (string.isNullOrEmpty())
                view.context.getString(defaultMessage, value)
            else string!!

    fun getStringOrDefault(view: View,
                           string: CharSequence?,
                           @StringRes defaultMessage: Int): String =
            if (string == null || string.toString().isEmpty())
                view.context.getString(defaultMessage)
            else string.toString()

    fun getStringOrDefault(view: View,
                           string: CharSequence?,
                           @StringRes defaultMessage: Int,
                           value: Int): String =
            if (string == null || string.toString().isEmpty())
                view.context.getString(defaultMessage, value)
            else string.toString()
}
