package com.github.jackparisi.droidbox.wrapper

import android.view.View
import android.view.ViewGroup

/**
 * Created by Giacomo Parisi on 25/10/17.
 * https://github.com/JackParisi
 */

data class DroidWrapperView(
        val view: View,
        val layoutParams: ViewGroup.LayoutParams
)