package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidbox.wrapper.toolbar.ToolbarDroidConfigurator
import com.github.jackparisi.droidbox.wrapper.toolbar.ToolbarDroidSettings
import com.github.jackparisi.droidbox.wrapper.toolbar.ToolbarDroidWrapper
import kotlin.reflect.KClass

/**
 * Created by Giacomo Parisi on 25/07/17.
 * https://github.com/JackParisi
 */
abstract class DroidWrapperService(val context: Context) {

    private var errorWrapper = ErrorDroidWrapper()
    private val loadingWrapper = LoadingDroidWrapper()
    private var toolbarWrapper = ToolbarDroidWrapper()

    fun <W : ViewDataBinding> wrapErrorLayout(viewModel: DroidViewModel, root: View, wrapperClass: KClass<W>): View {
        val wrapperRoot = createErrorWrapper(viewModel, wrapperClass)
        return errorWrapper.wrapLayout(
                DroidWrapperSettings(
                        viewModel,
                        root,
                        wrapperRoot,
                        context,
                        ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                        )
                )
        )

    }

    fun <W : ViewDataBinding> wrapLoadingLayout(viewModel: DroidViewModel, root: View, wrapperClass: KClass<W>): View {
        val wrapperRoot = createLoadingWrapper(viewModel, wrapperClass)
        return loadingWrapper.wrapLayout(
                DroidWrapperSettings(
                        viewModel,
                        root,
                        wrapperRoot,
                        context,
                        ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                        )
                )
        )
    }

    fun <W : ViewDataBinding> wrapToolbarLayout(viewModel: DroidViewModel, root: View, toolbarDroidConfigurator: ToolbarDroidConfigurator, wrapperClass: KClass<W>, overPageLayout: Boolean): View {
        val wrapperRoot = createToolbarWrapper(viewModel, toolbarDroidConfigurator, wrapperClass)
        return toolbarWrapper.wrapLayout(
                ToolbarDroidSettings(
                        viewModel,
                        root,
                        wrapperRoot,
                        context,
                        ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                        ),
                        overPageLayout
                )
        )
    }

    protected abstract fun <W : ViewDataBinding> createErrorWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): View?

    protected abstract fun <W : ViewDataBinding> createLoadingWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): View?

    protected abstract fun <W : ViewDataBinding> createToolbarWrapper(viewModel: DroidViewModel, toolbarDroidConfigurator: ToolbarDroidConfigurator, wrapperClass: KClass<W>): View?

    fun onViewDestroy() {
        errorWrapper.removeCallback()
        loadingWrapper.removeCallback()
    }
}