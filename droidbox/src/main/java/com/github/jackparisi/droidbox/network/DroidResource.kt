package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 09/07/2017.
 * https://github.com/JackParisi
 */
sealed class DroidResource<out T> {

    abstract fun <R> map(f: (T) -> R): DroidResource<R>

    data class Success<out T>(val data: T) : DroidResource<T>() {
        override fun <R> map(f: (T) -> R): DroidResource<R> = Success(f(data))
    }

    data class Error(val message: String) : DroidResource<Nothing>() {
        constructor(t: Throwable) : this(t.message ?: "")

        override fun <R> map(f: (Nothing) -> R): DroidResource<R> = this
    }

    object Loading : DroidResource<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): DroidResource<R> = this
    }

    object Empty : DroidResource<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): DroidResource<R> = this
    }
}

fun <T> DroidResource<T>.orElse(defaultValue: T): T =
        if (this is DroidResource.Success)
            data ?: defaultValue
        else
            defaultValue