package com.github.jackparisi.droidbox.logs

import android.util.Log
import com.google.firebase.crash.FirebaseCrash
import timber.log.Timber

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
class CrashReportingTree : Timber.Tree() {

    /**
     *
     * Send the exception to Firebase after the priority is not VERBOSE or DEBUG
     *
     * @param priority The priority value of the exception
     * @param tag The tag of the exception
     * @param message The error message of the exception
     * @param throwable The exception that need to be analyzed
     */
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