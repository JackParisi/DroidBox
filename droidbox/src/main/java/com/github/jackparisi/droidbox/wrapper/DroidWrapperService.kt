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

/**
 * Service that perform the wrap action for the standard DroidBox wrapper
 */
abstract class DroidWrapperService(val context: Context) {

    private var errorWrapper = ErrorDroidWrapper()
    private val loadingWrapper = LoadingDroidWrapper()
    private var toolbarWrapper = ToolbarDroidWrapper()

    /**
     *
     * Wrap the error layout with the root view
     *
     * @param viewModel DroidViewModel used for binding
     * @param root The root view that need the error view
     * @param wrapperClass The class of the wrapper (for take different error view based on the wrapper class)
     *
     * @return The root view wrapped with the error view
     */
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

    /**
     *
     * Wrap the loading layout with the root view
     *
     * @param viewModel DroidViewModel used for binding
     * @param root The root view that need the loading view
     * @param wrapperClass The class of the wrapper (for take different loading view based on the wrapper class)
     *
     * @return The root view wrapped with the loading view
     */
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

    /**
     *
     * Wrap the toolbar layout with the root view
     *
     * @param viewModel DroidViewModel used for binding
     * @param root The root view that need the toolbar
     * @param wrapperClass The class of the wrapper (for take different toolbar layout based on the wrapper class)
     *
     * @return The root view wrapped with the toolbar
     */
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

    /**
     *
     * Create the error view based on the wrapper class
     *
     * @param viewModel The view model used for binding
     * @param wrapperClass The wrapper class that need the error view
     *
     * @return The error view
     */
    protected abstract fun <W : ViewDataBinding> createErrorWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): View?

    /**
     *
     * Create the error view based on the wrapper class
     *
     * @param viewModel The view model used for binding
     * @param wrapperClass The wrapper class that need the loading view
     *
     * @return The loading view
     */
    protected abstract fun <W : ViewDataBinding> createLoadingWrapper(viewModel: DroidViewModel, wrapperClass: KClass<W>): View?


    /**
     *
     * Create the error view based on the wrapper class
     *
     * @param viewModel The view model used for binding
     * @param wrapperClass The wrapper class that need the toolbar
     *
     * @return The toolbar view
     */
    protected abstract fun <W : ViewDataBinding> createToolbarWrapper(viewModel: DroidViewModel, toolbarDroidConfigurator: ToolbarDroidConfigurator, wrapperClass: KClass<W>): View?


    /**
     *
     * Call this method for unbind the wrapper view from the view model
     */
    fun onViewDestroy() {
        errorWrapper.removeCallback()
        loadingWrapper.removeCallback()
    }
}