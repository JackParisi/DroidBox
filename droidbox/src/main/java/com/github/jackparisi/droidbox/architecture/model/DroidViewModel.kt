package com.github.jackparisi.droidbox.architecture.model

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidViewModel : ViewModel() {

    // True if error view is needed
    var error = ObservableBoolean()

    // Class type of the last error produced
    var lastError: Class<Throwable>? = null

    // Error message text
    var errorMessage = ObservableField<String>()

    // Retry button text
    var retryButtonMessage = ObservableField<String>()

    // True if loading view is needed
    var loading = ObservableBoolean()

    // Title text
    var title = ObservableInt()
}
