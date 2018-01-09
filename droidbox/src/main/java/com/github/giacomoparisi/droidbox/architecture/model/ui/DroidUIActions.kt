package com.github.giacomoparisi.droidbox.architecture.model.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.support.v4.app.FragmentActivity
import java.util.*
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 18/07/17.
 * https://github.com/giacomoParisi
 */

typealias UiAction = (FragmentActivity) -> Unit

open class DroidUIActions @Inject constructor() {

    // MutableLiveData for the UIAction lists
    private val delegate = MutableLiveData<List<UiAction>>()

    // List of UIAction that need to be delegate to the view
    private var list: MutableList<UiAction> = ArrayList()

    /**
     *
     * Add a UIAction to the list of the actions that are going to invoked on the view
     *
     * @param action UIAction that will be added to the list
     */
    private fun execute(action: UiAction) {
        list.add(action)
        delegate.value = list
    }

    operator fun invoke(action: UiAction) {
        execute(action)
    }

    /**
     *
     * Observe the list of actions and execute it on the owner view
     *
     * @param owner The view that observe the action list and perform it when actions are added
     * @param executor The executor function that execute the action
     */
    fun observe(owner: LifecycleOwner, executor: (UiAction) -> Unit) =
            delegate.observe(owner, Observer {
                list.forEach { executor(it) }
                list = ArrayList()
            })

    /**
     *
     * Observe forever the list of actions and execute it
     *
     * @param executor The executor function that execute the action
     */
    @MainThread fun observeForever(executor: (UiAction) -> Unit) =
            delegate.observeForever {
                list.forEach { executor(it) }
                list = ArrayList()
            }
}