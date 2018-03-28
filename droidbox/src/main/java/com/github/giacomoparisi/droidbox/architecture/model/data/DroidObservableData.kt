package com.github.giacomoparisi.droidbox.architecture.model.data

import android.arch.lifecycle.LiveData
import android.databinding.Observable
import android.databinding.ObservableField

/**
 * Created by Giacomo Parisi on 15/07/2017.
 * https://github.com/giacomoParisi
 */

class DroidObservableData<T : ObservableField<*>>(var data: T) : LiveData<T>() {

    // Callback for property changed event
    private var callback: Observable.OnPropertyChangedCallback? = null

    /**
     *
     * Set the callback for the value that will be called when the value is updated,
     * and removed when the context to which it is tied ends
     *
     * @param callback The callback that will be bind to the value
     */
    fun setCallback(callback: Observable.OnPropertyChangedCallback) {
        this.callback = callback
        data.addOnPropertyChangedCallback(callback)
    }

    override fun onInactive() {
        if (callback != null) {
            data.removeOnPropertyChangedCallback(callback!!)
        }
    }
}
