package com.github.giacomoparisi.droidbox.architecture.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIActions
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIManager

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidViewModel constructor(
        application: Application,
        val activityActions: DroidUIActions<androidx.fragment.app.FragmentActivity>,
        val fragmentActions: DroidUIActions<androidx.fragment.app.Fragment>,
        val droidUIManager: DroidUIManager) : AndroidViewModel(application) {

    open fun retryFromError() {
        droidUIManager.hideError()
    }
}