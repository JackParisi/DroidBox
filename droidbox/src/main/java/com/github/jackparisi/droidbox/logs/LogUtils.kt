package com.github.jackparisi.droidbox.logs

import com.github.jackparisi.droidbox.architecture.model.exception.ManagedException
import io.reactivex.exceptions.CompositeException
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

object LogUtils {
    fun initLoggingAndCrashReporting(debug: Boolean) {
        Timber.plant(if (debug)
            Timber.DebugTree()
        else
            CrashReportingTree())

        //        if (debug) {
        //            Thread.setDefaultUncaughtExceptionHandler((paramThread, paramThrowable) ->
        //            {
        //                Timber.e(paramThrowable);
        //                System.exit(2); //Prevents the service/app from freezing
        //            });
        //        }

        RxJavaPlugins.setErrorHandler(LogErrorHandler())
    }

    fun isConnectionError(t: Throwable): Boolean {
        if (t is UnknownHostException || t is SocketException || t is SocketTimeoutException
                || t is HttpException || t is TimeoutException) {
            return true
        }
        return false
    }

    fun isExceptionToBeLogged(t: Throwable): Boolean {
        if (t is ManagedException || isConnectionError(t)) {
            return false
        }
        if (t is CompositeException) {
            return existsExceptionToBeLogged((t).exceptions)
        }
        return true
    }

    private fun existsExceptionToBeLogged(exceptions: List<Throwable>): Boolean {
        return exceptions.any { isExceptionToBeLogged(it) }
    }
}
