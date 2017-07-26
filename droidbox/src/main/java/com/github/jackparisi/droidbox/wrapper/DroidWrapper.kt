package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 25/07/17.
 * https://github.com/JackParisi
 */
abstract class DroidWrapper {

    protected val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    abstract fun wrapLayout(viewModels: List<DroidViewModel>, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams = this.params): View
}