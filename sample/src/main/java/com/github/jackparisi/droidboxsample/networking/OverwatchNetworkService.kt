package com.github.jackparisi.droidboxsample.networking

import com.github.jackparisi.droidboxsample.database.overwatch.OverwatchStatsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */
interface OverwatchNetworkService {

    @GET("api/v3/u/{battletag}/blob")
    fun getAllOverwatchStats(@Path("battletag") battletag: String): Single<OverwatchStatsResponse>
}