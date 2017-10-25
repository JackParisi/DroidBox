package com.github.jackparisi.droidbox.wrapper.toolbar

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidbox.wrapper.DroidWrapperSettings
import com.github.jackparisi.droidbox.wrapper.DroidWrapperView

/**
 * Created by Giacomo Parisi on 20/10/17.
 * https://github.com/JackParisi
 */

class ToolbarDroidSettings(
        viewModel: DroidViewModel,
        pageLayout: DroidWrapperView,
        wrapperLayout: DroidWrapperView,
        context: Context,
        val overPageLayout: Boolean) : DroidWrapperSettings(viewModel, pageLayout, wrapperLayout, context) {

    companion object {

        fun getMatchParentParams() = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

        fun getWrapContentParams() = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        fun getMatchWrapParams() = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)

        fun getWrapMatchParams() = ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
    }
}