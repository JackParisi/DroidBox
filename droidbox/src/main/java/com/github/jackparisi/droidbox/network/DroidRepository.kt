package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 16/08/17.
 * https://github.com/JackParisi
 */

abstract class DroidRepository<P : DroidDataProvider<*>> {

    var databaseData: Any? = null
    var networkData: Any? = null
    abstract var dataProvider: P

    fun setData(databaseData: Any? = null, networkData: Any? = null) {
        dataProvider.setData(databaseData, networkData)
    }
}