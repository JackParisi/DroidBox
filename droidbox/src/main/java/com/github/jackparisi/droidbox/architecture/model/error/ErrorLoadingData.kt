package com.github.jackparisi.droidbox.architecture.model.error

import android.databinding.ObservableBoolean
import android.databinding.ObservableField

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
interface ErrorLoadingData {

    // True if loading view is needed
    val loading: ObservableBoolean

    // True if error view is needed
    val error: ObservableBoolean

    // The string value for the error message
    val errorMessage: ObservableField<String>

    // The string value for the error button message
    val errorButtonMessage: ObservableField<String>

    /**
     *
     * The retry function that need to be called when the user
     * wants to retry an action due to an error
     */
    fun retryFromUi()
}
