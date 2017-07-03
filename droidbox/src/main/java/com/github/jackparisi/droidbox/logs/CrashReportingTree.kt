package com.github.jackparisi.droidbox.logs

import android.util.Log
import com.google.firebase.crash.FirebaseCrash
import timber.log.Timber

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
class CrashReportingTree : Timber.Tree() {
     override fun log(priority: Int, tag: String, message: String, throwable: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        FirebaseCrash.logcat(priority, tag, message)

        if (throwable != null) {
            FirebaseCrash.report(throwable)
        }
    }
}