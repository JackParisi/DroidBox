package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 16/08/17.
 * https://github.com/JackParisi
 */

/**
 *
 * Data provider for a DroidRepository class.
 * Contains the data to run network and database requests.
 */
abstract class DroidDataProvider<R> {

    // The repository that need the data
    abstract var repository: R
    protected set

    // Map of data for database requests
    protected var databaseData: Map<String, String>? = null
    // Map of data for network request
    protected var networkData: Map<String, String>? = null

    /**
     *
     * Setup the data for the Data provider
     *
     * @param databaseData The database data map to set for requests
     * @param networkData The network data map to set for requests
     */
    fun setData(databaseData: Map<String, String>? = null, networkData: Map<String, String>? = null){
        this.databaseData = databaseData
        this.networkData = networkData
    }

}