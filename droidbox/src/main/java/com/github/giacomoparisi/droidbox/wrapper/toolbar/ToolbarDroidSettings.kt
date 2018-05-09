package com.github.giacomoparisi.droidbox.wrapper.toolbar

import android.content.Context
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperSettings
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperView

/**
 * Created by Giacomo Parisi on 20/10/17.
 * https://github.com/giacomoParisi
 */

class ToolbarDroidSettings(
        viewModel: DroidViewModel,
        pageLayout: DroidWrapperView,
        wrapperLayout: DroidWrapperView?,
        context: Context,
        val overPageLayout: Boolean,
        val pushDownContentAtIndex: Int) : DroidWrapperSettings(viewModel, pageLayout, wrapperLayout, context) {

    companion object {
        const val NONE = -1
    }
}