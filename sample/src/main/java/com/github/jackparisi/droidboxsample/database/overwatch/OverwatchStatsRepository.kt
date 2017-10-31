package com.github.jackparisi.droidboxsample.database.overwatch

import com.github.jackparisi.droidbox.network.DroidRepository
import com.github.jackparisi.droidbox.network.DroidRxDataProvider
import com.github.jackparisi.droidboxsample.networking.OverwatchNetworkService
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

class OverwatchStatsRepository @Inject constructor(
        private val overwatchNetworkService: OverwatchNetworkService
) : DroidRepository<DroidRxDataProvider<OverwatchStatsResponse>>() {

    var battletag: String? = null

    override var dataProvider = object : DroidRxDataProvider<OverwatchStatsResponse>() {

        override fun saveCallResult(data: OverwatchStatsResponse?) {}

        override fun shouldFetch(data: OverwatchStatsResponse?): Boolean = true

        override fun shouldLoadFromDbBeforeFetch(): Boolean = false

        override fun loadFromDb(data: Map<String, String>?): Flowable<OverwatchStatsResponse>? = null

        override fun fetchFromNetwork(data: Map<String, String>?): Single<OverwatchStatsResponse>? = overwatchNetworkService.getAllOverwatchStats(battletag ?: "")

    }
}