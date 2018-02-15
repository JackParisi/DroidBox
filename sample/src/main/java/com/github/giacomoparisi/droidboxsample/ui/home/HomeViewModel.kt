package com.github.giacomoparisi.droidboxsample.ui.home

import android.app.Application
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIActions
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIManager
import com.github.giacomoparisi.droidboxsample.core.Navigator
import com.github.giacomoparisi.droidboxsample.core.droidbox.DroidBoxSampleViewModel
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
class HomeViewModel @Inject constructor(
        application: Application,
        activityActions: DroidUIActions<FragmentActivity>,
        fragmentActions: DroidUIActions<Fragment>,
        droidUIManager: DroidUIManager,
        navigator: Navigator) :
        DroidBoxSampleViewModel(
                application,
                activityActions,
                fragmentActions,
                droidUIManager,
                navigator)