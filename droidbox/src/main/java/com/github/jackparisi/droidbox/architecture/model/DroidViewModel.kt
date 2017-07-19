package com.github.jackparisi.droidbox.architecture.model

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidViewModel : ViewModel() {
    var error = ObservableBoolean()

    var lastError: Class<Throwable>? = null

    var errorMessage = ObservableField<String>()

    var retryButtonMessage = ObservableField<String>()

    var loading = ObservableBoolean()
}
