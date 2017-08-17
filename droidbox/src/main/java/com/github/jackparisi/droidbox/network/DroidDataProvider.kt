package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 16/08/17.
 * https://github.com/JackParisi
 */

abstract class DroidDataProvider<D>{

    abstract var repository: D
    protected set

    protected var databaseData: Map<String, String>? = null
    protected var networkData: Map<String, String>? = null

    fun setData(databaseData: Map<String, String>? = null, networkData: Map<String, String>? = null){
        this.databaseData = databaseData
        this.networkData = networkData
    }

}