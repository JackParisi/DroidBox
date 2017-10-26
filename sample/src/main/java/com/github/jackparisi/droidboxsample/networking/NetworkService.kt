package com.github.jackparisi.droidboxsample.networking

import com.github.jackparisi.droidboxsample.database.games.SteamGames
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Giacomo Parisi on 24/10/17.
 * https://github.com/JackParisi
 */

interface NetworkService {

    @GET("ISteamApps/GetAppList/v0002/")
    fun getSteamGames(): Single<SteamGames>
}