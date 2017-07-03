package com.github.jackparisi.droidbox.architecture.model.error

import android.databinding.ObservableBoolean
import android.databinding.ObservableField

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
interface ErrorLoadingData {
    val loading: ObservableBoolean

    val error: ObservableBoolean

    fun retryFromUi()

    val errorMessage: ObservableField<String>

    val errorButtonMessage: ObservableField<String>
}
