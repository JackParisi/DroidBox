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

    var errorWrapper = ErrorDroidWrapper()
    val loadingWrapper = LoadingDroidWrapper()
    var toolbarWrapper = ToolbarDroidWrapper()

    protected fun <W : ViewDataBinding> wrapErrorLayout(viewModel: DroidViewModel, root: View, wrapperClass: KClass<W>): View {
        val wrapperRoot = createErrorWrapper(wrapperClass)
        return errorWrapper.wrapLayout(viewModel, root, wrapperRoot, context)
    }

    protected fun <W : ViewDataBinding> wrapLoadingLayout(viewModel: DroidViewModel, root: View, wrapperClass: KClass<W>): View {
        val wrapperRoot = createLoadingWrapper(wrapperClass)
        return loadingWrapper.wrapLayout(viewModel, root, wrapperRoot, context)
    }

    protected fun <W : ViewDataBinding> wrapToolbarLayout(viewModel: DroidViewModel, root: View, wrapperClass: KClass<W>): View {
        val wrapperRoot = createToolbarWrapper(viewModel, wrapperClass)
        return toolbarWrapper.wrapLayout(viewModel, root, wrapperRoot, context)
    }

    protected abstract fun <W : ViewDataBinding> createErrorWrapper(wrapperClass: KClass<W>): View?

    protected abstract fun <W : ViewDataBinding> createLoadingWrapper(wrapperClass: KClass<W>): View?

    protected abstract fun <W : ViewDataBinding> createToolbarWrapper(toolbarViewModel: DroidViewModel, wrapperClass: KClass<W>): View?

    fun onViewDestroy(){
        errorWrapper.removeCallback()
        loadingWrapper.removeCallback()
    }
}