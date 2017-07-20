package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 09/07/2017.
 * https://github.com/JackParisi
 */
sealed class DroidResource<out T> {

    abstract fun <R> map(f: (T) -> R): DroidResource<R>

    data class Network<out T>(val data: T) : DroidResource<T>() {
        override fun <R> map(f: (T) -> R): DroidResource<R> = Network(f(data))
    }

    data class NetworkError(val message: String) : DroidResource<Nothing>() {
        constructor(t: Throwable) : this(t.message ?: "")

        override fun <R> map(f: (Nothing) -> R): DroidResource<R> = this
    }

    object Loading : DroidResource<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): DroidResource<R> = this
    }

    object Empty : DroidResource<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): DroidResource<R> = this
    }

    data class Database<out T>(val data: T) : DroidResource<T>() {
        override fun <R> map(f: (T) -> R): DroidResource<R> = Database(f(data))
    }
}

fun <T> DroidResource<T>.orElse(defaultValue: T): T =
        if (this is DroidResource.Network)
            data ?: defaultValue
        else
            defaultValue