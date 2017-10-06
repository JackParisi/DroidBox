package com.github.jackparisi.droidbox.utility

import android.content.Context
import android.util.TypedValue

/**
 * Created by Giacomo Parisi on 21/08/17.
 * https://github.com/JackParisi
 */

/**
 *
 * Convert the float value for dp to pixel
 *
 * @param dp The value in dp
 * @param context The context object for access to displayMetrics
 * @return The converted value in pixels
 */
fun dpToPx(dp: Float, context: Context): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)