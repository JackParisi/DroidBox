package com.github.jackparisi.droidbox.architecture

import android.app.Activity
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidBoxViewModel
import com.github.jackparisi.droidbox.architecture.model.error.ErrorLoadingWrapper

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidBoxActivity : Activity(), LifecycleRegistryOwner {

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    protected var errorLoadingWrapper: ErrorLoadingWrapper? = null

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    protected fun wrapLayout(viewModel: DroidBoxViewModel, root: View): View {
        errorLoadingWrapper = ErrorLoadingWrapper()
        val wrapperRoot = createErrorLoadingWrapper(viewModel)
        return errorLoadingWrapper!!.wrapLayout(viewModel, root, wrapperRoot)
    }

    protected abstract fun createErrorLoadingWrapper(viewModel: DroidBoxViewModel): ViewGroup

    override fun onDestroy() {
        super.onDestroy()
        errorLoadingWrapper?.removeCallbacks()
    }
}