package com.github.giacomoparisi.droidboxsample.core

import android.app.Activity
import android.app.Application
import com.github.giacomoparisi.droidbox.architecture.dagger.DroidDaggerInjector
import com.github.giacomoparisi.droidboxsample.core.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 14/12/17.
 * https://github.com/giacomoParisi
 */
class DroidBoxSampleApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        // init dagger
        object : DroidDaggerInjector() {
            override fun injectComponent() {
                DaggerAppComponent.builder()
                        .application(this@DroidBoxSampleApp)
                        .build()
                        .inject(this@DroidBoxSampleApp)
            }
        }.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}
