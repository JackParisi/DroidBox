package com.github.jackparisi.droidbox.wrapper.toolbar

import android.content.Context
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
        val overPageLayout: Boolean) : DroidWrapperSettings(viewModel, pageLayout, wrapperLayout, context)