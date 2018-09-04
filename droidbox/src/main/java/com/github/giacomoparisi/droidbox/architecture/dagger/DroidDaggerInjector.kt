package com.github.giacomoparisi.droidbox.architecture.dagger

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

// Handle application dagger injection
abstract class DroidDaggerInjector {

    abstract fun injectComponent()

    fun init(application: Application) {
        injectComponent()
        application
                .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityPaused(activity: Activity?) {}

                    override fun onActivityResumed(activity: Activity?) {}

                    override fun onActivityStarted(activity: Activity?) {}

                    override fun onActivityDestroyed(activity: Activity?) {}

                    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

                    override fun onActivityStopped(activity: Activity?) {}

                    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                        activity?.let { handleInjection(it) }
                    }

                })
    }

    private fun handleInjection(activity: Activity) {
        if (activity.javaClass.isAnnotationPresent(Injectable::class.java)) {
            AndroidInjection.inject(activity)
        }
        if (activity is androidx.fragment.app.FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                    object : androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment,
                                                       savedInstanceState: Bundle?) {
                            if (f.javaClass.isAnnotationPresent(Injectable::class.java)) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true)
        }
    }
}
