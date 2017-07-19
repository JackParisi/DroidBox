package com.github.jackparisi.droidbox.architecture

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidbox.architecture.model.error.ErrorLoadingWrapper

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidFragment : LifecycleFragment() {

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    protected var errorLoadingWrapper: ErrorLoadingWrapper? = null

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    protected fun wrapLayout(viewModel: DroidViewModel, root: View): View {
        errorLoadingWrapper = ErrorLoadingWrapper()
        val wrapperRoot = createErrorLoadingWrapper(viewModel)
        return errorLoadingWrapper!!.wrapLayout(viewModel, root, wrapperRoot)
    }

    protected abstract fun createErrorLoadingWrapper(viewModel: DroidViewModel): ViewGroup

    override fun onDestroy() {
        super.onDestroy()
        errorLoadingWrapper?.removeCallbacks()
    }
}
