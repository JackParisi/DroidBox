package com.github.jackparisi.droidbox.architecture

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.LifecycleRegistry
import com.github.jackparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidActivity<out W : DroidWrapperService> : LifecycleActivity() {

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    abstract protected val wrapper: W

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }
}