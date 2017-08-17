package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 16/08/17.
 * https://github.com/JackParisi
 */

abstract class DroidRepository<P : DroidDataProvider<*>> {

    abstract var dataProvider: P

    fun setData(databaseData: Map<String, String>? = null, networkData: Map<String, String>? = null) {
        dataProvider.setData(databaseData, networkData)
    }
}