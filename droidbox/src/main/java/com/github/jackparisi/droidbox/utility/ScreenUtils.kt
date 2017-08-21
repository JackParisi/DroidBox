package com.github.jackparisi.droidbox.utility

import android.content.Context
import android.util.TypedValue

/**
 * Created by Giacomo Parisi on 21/08/17.
 * https://github.com/JackParisi
 */


fun dpToPx(dp: Float, context: Context): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)