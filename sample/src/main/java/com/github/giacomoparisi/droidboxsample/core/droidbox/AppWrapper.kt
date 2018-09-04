package com.github.giacomoparisi.droidboxsample.core.droidbox

import android.content.Context
import androidx.databinding.ViewDataBinding

import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperService
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperView
import com.github.giacomoparisi.droidbox.wrapper.toolbar.ToolbarDroidConfigurator

/**
 * Created by Giacomo Parisi on 14/12/17.
 * https://github.com/giacomoParisi
 */
class AppWrapper(context: Context) : DroidWrapperService(context) {

    override fun <W : ViewDataBinding> createErrorWrapper(
            viewModel: DroidViewModel,
            wrapperClass: Class<W>): DroidWrapperView? {
        return null
    }

    override fun <W : ViewDataBinding> createLoadingWrapper(
            viewModel: DroidViewModel,
            wrapperClass: Class<W>): DroidWrapperView? {

        return null
    }

    override fun <W : ViewDataBinding> createToolbarWrapper(
            viewModel: DroidViewModel,
            toolbarDroidConfigurator: ToolbarDroidConfigurator,
            wrapperClass: Class<W>): DroidWrapperView? {
        return null
    }
}
