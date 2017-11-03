package com.github.jackparisi.droidbox.architecture.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.github.jackparisi.droidbox.architecture.model.ui.DroidUiActions
import com.github.jackparisi.droidbox.architecture.model.ui.UiManager

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidViewModel(application: Application) : AndroidViewModel(application) {

    val droidUiActions = DroidUiActions()

    val uiManager = UiManager()

    fun retryFromError() {
        uiManager.hideError()
    }
}
