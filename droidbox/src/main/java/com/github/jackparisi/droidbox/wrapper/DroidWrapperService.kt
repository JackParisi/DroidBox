package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.databinding.ViewDataBinding
import android.view.View
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import kotlin.reflect.KClass

/**
 * Created by Giacomo Parisi on 25/07/17.
 * https://github.com/JackParisi
 */
abstract class DroidWrapperService(val context: Context) {

    var errorLoadingWrapper = ErrorLoadingDroidWrapper()
    var toolbarWrapper = ToolbarDroidWrapper()

    protected fun <W : ViewDataBinding> wrapErrorLoadingLayout(viewModel: DroidViewModel, root: View, wrapperClass: KClass<W>): View {
        val wrapperRoot = createErrorLoadingWrapper(viewModel, wrapperClass)
        return errorLoadingWrapper.wrapLayout(viewModel, root, wrapperRoot, context)
    }

    protected fun <W : ViewDataBinding> wrapToolbarLayout(viewModel: DroidViewModel, root: View, wrapperClass: KClass<W>): View {
        val wrapperRoot = createErrorLoadingWrapper(viewModel, wrapperClass)
        return toolbarWrapper.wrapLayout(viewModel, root, wrapperRoot, context)
    }

    protected abstract fun <W : ViewDataBinding> createErrorLoadingWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): View?

    protected abstract fun <W : ViewDataBinding> createToolbarWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): View?

    fun onViewDestroy(){
        errorLoadingWrapper.removeCallbacks()
    }
}