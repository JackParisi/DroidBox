package com.github.jackparisi.droidboxsample.database.games

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 25/10/17.
 * https://github.com/JackParisi
 */

data class GameList(@field:SerializedName("apps") val games: List<Game?>? = null)