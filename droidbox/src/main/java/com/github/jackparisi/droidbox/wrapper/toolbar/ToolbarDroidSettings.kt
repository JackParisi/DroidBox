package com.github.jackparisi.droidbox.wrapper.toolbar

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidbox.wrapper.DroidWrapperSettings

/**
 * Created by Giacomo Parisi on 20/10/17.
 * https://github.com/JackParisi
 */

class ToolbarDroidSettings(
        viewModel: DroidViewModel,
        pageLayout: View,
        wrapperLayout: View?,
        context: Context,
        params: ViewGroup.LayoutParams,
        val overPageLayout: Boolean) : DroidWrapperSettings(viewModel, pageLayout, wrapperLayout, context, params)