package com.github.giacomoparisi.droidboxsample.core.droidbox

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIActions
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIManager
import com.github.giacomoparisi.droidboxsample.core.Navigator
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 08/01/18.
 * https://github.com/giacomoParisi
 */
open class DroidBoxSampleViewModel @Inject constructor(
        application: Application,
        activityActions: DroidUIActions<androidx.fragment.app.FragmentActivity>,
        fragmentActions: DroidUIActions<androidx.fragment.app.Fragment>,
        droidUIManager: DroidUIManager,
        val navigator: Navigator) : DroidViewModel(application, activityActions, fragmentActions, droidUIManager) {
}
