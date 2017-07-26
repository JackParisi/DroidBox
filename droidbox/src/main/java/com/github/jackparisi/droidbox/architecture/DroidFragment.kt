package com.github.jackparisi.droidbox.architecture

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import com.github.jackparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidFragment<out W : DroidWrapperService> : LifecycleFragment() {

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    abstract protected val wrapper: W

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }
}
