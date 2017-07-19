package com.github.jackparisi.droidbox.architecture.model.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.support.v4.app.FragmentActivity
import java.util.ArrayList

/**
 * Created by Giacomo Parisi on 18/07/17.
 * https://github.com/JackParisi
 */

typealias UiAction = (FragmentActivity) -> Unit

class DroidUiActions {
    private val delegate = MutableLiveData<List<UiAction>>()

    private var list: MutableList<UiAction> = ArrayList()

    fun execute(action: UiAction) {
        list.add(action)
        delegate.value = list
    }

    operator fun invoke(action: UiAction) {
        execute(action)
    }

    fun observe(owner: LifecycleOwner, executor: (UiAction) -> Unit) =
            delegate.observe(owner, Observer {
                list.forEach { executor(it) }
                list = ArrayList()
            })

    @MainThread fun observeForever(executor: (UiAction) -> Unit) =
            delegate.observeForever {
                list.forEach { executor(it) }
                list = ArrayList()
            }
}