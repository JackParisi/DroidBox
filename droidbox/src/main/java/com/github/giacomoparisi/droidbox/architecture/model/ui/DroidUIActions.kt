package com.github.giacomoparisi.droidbox.architecture.model.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.annotation.MainThread
import java.util.*
import javax.inject.Inject

        /**
         * Created by Giacomo Parisi on 18/07/17.
         * https://github.com/giacomoParisi
         */

typealias UiAction<T> = (T) -> Unit

open class DroidUIActions<V> @Inject constructor() {

    // MutableLiveData for the UIAction lists
    private val delegate = MutableLiveData<List<UiAction<V>>>()

    // List of UIAction that need to be delegate to the view for activity
    private var actionsList: MutableList<UiAction<V>> = ArrayList()

    /**
     *
     * Add a UIAction to the activityActionsList of the actions that are going to invoked on the view
     *
     * @param action UIAction that will be added to the activityActionsList
     */
    private fun execute(action: UiAction<V>) {
        actionsList.add(action)
        delegate.value = actionsList
    }

    operator fun invoke(action: UiAction<V>) {
        execute(action)
    }

    /**
     *
     * Observe the activityActionsList of actions and execute it on the owner view
     *
     * @param owner The view that observe the action activityActionsList and perform it when actions are added
     * @param executor The executor function that execute the action
     */
    fun observe(owner: LifecycleOwner, executor: (UiAction<V>) -> Unit) =
            delegate.observe(owner, Observer {
                actionsList.forEach { executor(it) }
                actionsList = ArrayList()
            })

    /**
     *
     * Observe forever the activityActionsList of actions and execute it
     *
     * @param executor The executor function that execute the action
     */
    @MainThread
    fun observeForever(executor: (UiAction<V>) -> Unit) =
            delegate.observeForever {
                actionsList.forEach { executor(it) }
                actionsList = ArrayList()
            }
}