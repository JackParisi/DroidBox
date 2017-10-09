package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 25/07/17.
 * https://github.com/JackParisi
 */

/**
 *
 * Wrapper class that allows to combine 2 layouts (specific page layout with a common layout)
 */
abstract class DroidWrapper {

    // LayoutParams for the wrapper layout
    protected val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    /**
     *
     * Wrap the page layout with the wrapper layout using the params and bind the wrapper with the viewModel
     *
     * @param viewModel The droidViewModel of the page to bind the wrapper
     * @param pageLayout The layout of the page
     * @param context The context of the page
     * @param params The layout params for the wrapper layout
     *
     * @return Return the view that wrap the page with the wrapper layout
     */
    abstract fun wrapLayout(viewModel: DroidViewModel, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams = this.params): View
}