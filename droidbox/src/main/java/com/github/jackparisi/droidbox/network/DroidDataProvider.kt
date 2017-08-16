package com.github.jackparisi.droidbox.network

/**
 * Created by Giacomo Parisi on 16/08/17.
 * https://github.com/JackParisi
 */

abstract class DroidDataProvider<D>{

    abstract var result: D
    protected set

    protected var databaseData: Any? = null
    protected var networkData: Any? = null

    fun setData(databaseData: Any? = null, networkData: Any? = null){
        this.databaseData = databaseData
        this.networkData = networkData
    }

}