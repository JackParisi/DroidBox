package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 20/10/17.
 * https://github.com/JackParisi
 */

/**
 *
 * Class of date used for perform a wrap
 *
 * @param viewModel The droidViewModel of the page to bind the wrapper
 * @param pageLayout The layout of the page
 * @param context The context of the page
 *
 * @return Return the view that wrap the page with the wrapper layout
 */
open class DroidWrapperSettings(
        val viewModel: DroidViewModel,
        val pageLayout: DroidWrapperView,
        val wrapperLayout: DroidWrapperView,
        val context: Context
) {
    companion object {

        fun getMatchParentParams() = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        fun getWrapContentParams() = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        fun getMatchWrapParams() = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        fun getWrapMatchParams() = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}