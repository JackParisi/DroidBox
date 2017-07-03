package com.github.jackparisi.droidbox.architecture.model

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

open class DroidBoxViewModel(val lifecycleObserver: LifecycleObserver) : ViewModel(), LifecycleObserver{
    var error = ObservableBoolean()

    var lastError: Class<Throwable>? = null

    var errorMessage = ObservableField<String>()

    var retryButtonMessage = ObservableField<String>()

    var loading = ObservableBoolean();
}
