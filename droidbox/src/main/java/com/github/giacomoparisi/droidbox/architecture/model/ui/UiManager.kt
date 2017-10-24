package com.github.giacomoparisi.droidbox.architecture.model.ui

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.architecture.model.exception.ManagedException

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */
class UiManager(private val application: Application) {

    // True if error view is needed
    var error = ObservableBoolean()

    // Class type of the last error produced
    var lastError: Class<Throwable>? = null

    // Id of the last error produced
    var lastErrorCode: Int = 0

    // Error message text
    var errorMessage = ObservableField<String>()

    // Retry button text
    var retryButtonMessage = ObservableField<String>()

    // True if loading view is needed
    var loading = ObservableBoolean()

    // Title text
    var title = ObservableInt()

    var defaultErrorMessage = R.string.ERROR_DefaultMessage
    var defaultRetryMessage = R.string.ERROR_Retry

    fun showError(throwable: Throwable, errorCode: Int = 0) {
        hideLoading()
        getErrorMessage(throwable)
        getRetryMessage(throwable)
        lastError = throwable.javaClass
        lastErrorCode = errorCode
        error.set(true)
    }

    fun showLoading() {
        loading.set(true)
    }

    fun hideLoading() {
        loading.set(false)
    }

    fun hideError() {
        error.set(false)
    }

    private fun getErrorMessage(throwable: Throwable) {
        if (throwable is ManagedException) {
            if (throwable.errorMessageRes != 0) {
                errorMessage.set(application.getString(throwable.errorMessageRes))
            } else {
                errorMessage.set(throwable.errorMessage)
            }
        } else {
            errorMessage.set(application.getString(defaultErrorMessage))
        }
    }

    private fun getRetryMessage(throwable: Throwable) {
        if (throwable is ManagedException) {
            if (throwable.retryButtonLabelId != 0) {
                retryButtonMessage.set(application.getString(throwable.retryButtonLabelId))
            } else {
                retryButtonMessage.set(throwable.retryButtonLabel)
            }
        } else {
            retryButtonMessage.set(application.getString(defaultRetryMessage))
        }
    }
}
