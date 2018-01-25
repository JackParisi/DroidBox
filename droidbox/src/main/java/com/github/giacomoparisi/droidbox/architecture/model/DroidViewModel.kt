package com.github.giacomoparisi.droidbox.architecture.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIActions
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIManager

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidViewModel constructor(
        application: Application,
        val activityActions: DroidUIActions<FragmentActivity>,
        val fragmentActions: DroidUIActions<Fragment>,
        val droidUIManager: DroidUIManager) : AndroidViewModel(application) {

    open fun retryFromError() {
        droidUIManager.hideError()
    }
}