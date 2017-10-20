package com.github.jackparisi.droidbox.architecture.model

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.github.jackparisi.droidbox.R
import com.github.jackparisi.droidbox.architecture.model.exception.ManagedException

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
    var errorMessage = ObservableInt()

    // Retry button text
    var retryButtonMessage = ObservableInt()

    // True if loading view is needed
    var loading = ObservableBoolean()

    // Title text
    var title = ObservableInt()

    var defaultErrorMessage = R.string.ERROR_DefaultMessage
    var defaultRetryMessage = R.string.ERROR_Retry

    fun showError(throwable: Throwable) {
        hideLoading()
        getErrorMessage(throwable)
        getRetryMessage(throwable)
        lastError = throwable.javaClass
        error.set(true)
    }

    fun showLoading() {
        loading.set(true)
    }

    fun hideLoading() {
        loading.set(false)
    }

    fun retryFromError() {
        hideError()
    }

    fun hideError() {
        error.set(false)
    }

    private fun getErrorMessage(throwable: Throwable) {
        if (throwable is ManagedException) {
            errorMessage.set(throwable.errorMessageRes)
        } else {
            errorMessage.set(defaultErrorMessage)
        }
    }

    private fun getRetryMessage(throwable: Throwable) {
        if (throwable is ManagedException) {
            retryButtonMessage.set(throwable.retryButtonLabelId)
        } else {
            retryButtonMessage.set(defaultRetryMessage)
        }
    }
}
