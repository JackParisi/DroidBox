package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 09/07/2017.
 * https://github.com/JackParisi
 */

open class DroidResource<T>(val data: T? = null, val errorMessage: String? = null, val isFetchedFromNetwork: Boolean = true) {

    class NetworkResource<T>(data: T) : DroidResource<T>(data = data)

    class NetworkErrorResource(errorMessage: String?) : DroidResource<Nothing>(data = null, errorMessage = errorMessage) {
        constructor(t: Throwable) : this(t.message ?: "")
    }

    class DatabaseResource<T>(data: T) : DroidResource<T>(data = data, isFetchedFromNetwork = false)
}