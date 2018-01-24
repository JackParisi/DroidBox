package com.github.giacomoparisi.droidbox.architecture.model.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import java.util.*
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 18/07/17.
 * https://github.com/giacomoParisi
 */

typealias UiAction = (FragmentActivity) -> Unit

typealias UiFragmentAction = (Fragment) -> Unit

open class DroidUIActions @Inject constructor() {

    // MutableLiveData for the UIAction lists
    private val delegate = MutableLiveData<List<UiAction>>()

    // List of UIAction that need to be delegate to the view for activity
    private var activityActionsList: MutableList<UiAction> = ArrayList()
    private var fragmentActionsList: MutableList<UiFragmentAction> = ArrayList()

    /**
     *
     * Add a UIAction to the activityActionsList of the actions that are going to invoked on the view
     *
     * @param action UIAction that will be added to the activityActionsList
     */
    private fun execute(action: UiAction) {
        activityActionsList.add(action)
        delegate.value = activityActionsList
    }

    operator fun invoke(action: UiAction) {
        execute(action)
    }

    /**
     *
     * Observe the activityActionsList of actions and execute it on the owner view
     *
     * @param owner The view that observe the action activityActionsList and perform it when actions are added
     * @param executor The executor function that execute the action
     */
    fun observe(owner: LifecycleOwner, executor: (UiAction) -> Unit) =
            delegate.observe(owner, Observer {
                activityActionsList.forEach { executor(it) }
                activityActionsList = ArrayList()
            })

    fun observeFragment(owner: LifecycleOwner, executor: (UiFragmentAction) -> Unit) =
            delegate.observe(owner, Observer {
                fragmentActionsList.forEach { executor(it) }
                fragmentActionsList = ArrayList()
            })

    /**
     *
     * Observe forever the activityActionsList of actions and execute it
     *
     * @param executor The executor function that execute the action
     */
    @MainThread fun observeForever(executor: (UiAction) -> Unit) =
            delegate.observeForever {
                activityActionsList.forEach { executor(it) }
                activityActionsList = ArrayList()
            }

    @MainThread
    fun observeForeverFragment(executor: (UiFragmentAction) -> Unit) =
            delegate.observeForever {
                fragmentActionsList.forEach { executor(it) }
                fragmentActionsList = ArrayList()
            }
}