package com.github.jackparisi.droidboxsample.core

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidbox.wrapper.DroidWrapperService
import com.github.jackparisi.droidbox.wrapper.DroidWrapperView
import com.github.jackparisi.droidbox.wrapper.toolbar.ToolbarDroidConfigurator
import com.github.jackparisi.droidbox.wrapper.toolbar.ToolbarDroidSettings
import com.github.jackparisi.droidboxsample.R
import com.github.jackparisi.droidboxsample.databinding.ToolbarBinding
import kotlin.reflect.KClass

/**
 * Created by Giacomo Parisi on 25/10/17.
 * https://github.com/JackParisi
 */

class DroidBoxSampleWrapper(context: Context) : DroidWrapperService(context) {

    override fun <W : ViewDataBinding> createErrorWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): DroidWrapperView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <W : ViewDataBinding> createLoadingWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): DroidWrapperView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun <W : ViewDataBinding> createToolbarWrapper(viewModel: DroidViewModel, toolbarDroidConfigurator: ToolbarDroidConfigurator, wrapperClass: KClass<W>): DroidWrapperView {
        val wrapper = DataBindingUtil.inflate<ToolbarBinding>(LayoutInflater.from(context), R.layout.toolbar, null, false)
        wrapper.viewModel = viewModel

        return DroidWrapperView(
                wrapper.root,
                ToolbarDroidSettings.getMatchWrapParams()
        )
    }

}