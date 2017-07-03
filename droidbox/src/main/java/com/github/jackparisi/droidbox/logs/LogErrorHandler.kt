package com.github.jackparisi.droidbox.logs

import io.reactivex.annotations.NonNull
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.functions.Consumer

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
class LogErrorHandler : Consumer<Throwable> {
    @Throws(Exception::class)
    override fun accept(@NonNull e: Throwable) {
        if (e is UndeliverableException) {
            val cause : Throwable = e.cause!!
            if (LogUtils.isConnectionError(cause)) {
                // fine, irrelevant network problem or API that throws on cancellation
                return
            }
            if (cause is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return
            }
        }
        Thread.currentThread().uncaughtExceptionHandler
                .uncaughtException(Thread.currentThread(), e)
    }
}
