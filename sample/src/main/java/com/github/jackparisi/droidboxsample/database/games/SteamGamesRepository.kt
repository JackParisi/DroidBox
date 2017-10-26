package com.github.jackparisi.droidboxsample.database.games

import com.github.jackparisi.droidbox.network.DroidRepository
import com.github.jackparisi.droidbox.network.DroidRxDataProvider
import com.github.jackparisi.droidboxsample.networking.NetworkService
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 26/10/17.
 * https://github.com/JackParisi
 */

class SteamGamesRepository @Inject constructor(
        private val networkService: NetworkService
) : DroidRepository<DroidRxDataProvider<SteamGames>>() {

    override var dataProvider = object : DroidRxDataProvider<SteamGames>() {

        override fun saveCallResult(data: SteamGames?) {}

        override fun shouldFetch(data: SteamGames?): Boolean = true

        override fun shouldLoadFromDbBeforeFetch(): Boolean = false

        override fun loadFromDb(data: Map<String, String>?): Flowable<SteamGames>? = null

        override fun fetchFromNetwork(data: Map<String, String>?): Single<SteamGames>? = networkService.getSteamGames()

    }
}