package com.github.giacomoparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 16/08/17.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Repository class that takes care of managing how to obtain a specific data stream
 * through the help of a data provider
 */
abstract class DroidRepository<P : DroidDataProvider<*>> {

    // The data provider reference for the repository
    abstract var dataProvider: P

    /**
     *
     * Setup the data for the network or database requests in the data provider
     *
     * @param databaseData The database data map to set for requests
     * @param networkData The network data map to set for requests
     */
    fun setData(databaseData: Map<String, String>? = null, networkData: Map<String, String>? = null) {
        dataProvider.setData(databaseData, networkData)
    }
}