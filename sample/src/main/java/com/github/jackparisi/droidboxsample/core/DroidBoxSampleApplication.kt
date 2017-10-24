package com.github.jackparisi.droidboxsample.core

import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import com.github.jackparisi.droidbox.architecture.DroidActivity
import com.github.jackparisi.droidbox.logs.LogUtils
import com.github.jackparisi.droidboxsample.BuildConfig

/**
 * Created by Giacomo Parisi on 24/10/17.
 * https://github.com/JackParisi
 */

class DroidBoxSampleApplication : Application() {

    lateinit var component: DroidBoxSampleComponent
        private set

    override fun onCreate() {
        super.onCreate()

        // Init timber logging
        LogUtils.initLoggingAndCrashReporting(BuildConfig.DEBUG)

        // Init dagger component
        component = DaggerDroidBoxSampleComponent.builder()
                .droidBoxSampleModule(DroidBoxSampleModule(this))
                .build()
    }
}

val Context.component: DroidBoxSampleComponent
    get() = (applicationContext as DroidBoxSampleApplication).component

val Fragment.component: DroidBoxSampleComponent
    get() = activity.component

val DroidActivity<*>.component: DroidBoxSampleComponent
    get() = application.component