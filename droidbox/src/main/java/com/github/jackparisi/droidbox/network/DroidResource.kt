package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 09/07/2017.
 * https://github.com/JackParisi
 */

/**
 *
 * Wrapper class for repository's response objects (network or database).
 * Allows to add additional data and information to a repository's response objects
 *
 * @param data The response data
 * @param errorMessage The request's error message
 * @param isFetchedFromNetwork True if the data was fetched form network
 */
open class DroidResource<out T>(
        val data: T? = null,
        val errorMessage: String? = null,
        val isFetchedFromNetwork: Boolean = true) {

    /**
     *
     * Used for a positive response from the network (200)
     *
     * @param data The response data
     */
    class NetworkResource<out T>(data: T) : DroidResource<T>(data = data)

    /**
     *
     * Used for a error response from the network
     *
     * @param errorMessage The error message returned from the network request
     */
    class NetworkErrorResource(errorMessage: String?) : DroidResource<Nothing>(data = null, errorMessage = errorMessage) {

        /**
         *
         * @param throwable The exception raised from the network request
         */
        constructor(throwable: Throwable) : this(throwable.message ?: "")
    }

    /**
     *
     * Used for a response from the database after load data from it
     *
     * @param data The response data
     */
    class DatabaseResource<out T>(data: T) : DroidResource<T>(data = data, isFetchedFromNetwork = false)
}