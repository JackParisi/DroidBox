package com.github.jackparisi.droidboxsample.database.games

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 25/10/17.
 * https://github.com/JackParisi
 */

class Game(
        @field:SerializedName("appid") val id: Int? = null,
        @field:SerializedName("name") val name: String? = null
) {

    val banner = "http://cdn.akamai.steamstatic.com/steam/apps/$id/header.jpg"
}