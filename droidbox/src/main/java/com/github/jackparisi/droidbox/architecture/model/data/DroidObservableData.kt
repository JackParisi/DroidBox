package com.github.jackparisi.droidbox.architecture.model.data

import android.arch.lifecycle.LiveData
import android.databinding.Observable
import android.databinding.ObservableField

/**
 * Created by Giacomo Parisi on 15/07/2017.
 * https://github.com/JackParisi
 */

class DroidObservableData<T : ObservableField<*>>(var data: T) : LiveData<T>() {

    private var callback: Observable.OnPropertyChangedCallback? = null

    fun setCallback(callback: Observable.OnPropertyChangedCallback) {
        this.callback = callback
        data.addOnPropertyChangedCallback(callback)
    }

    override fun onInactive() {
        if (callback != null) {
            data.removeOnPropertyChangedCallback(callback)
        }
    }
}
