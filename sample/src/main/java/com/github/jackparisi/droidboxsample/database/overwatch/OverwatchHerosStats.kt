package com.github.jackparisi.droidboxsample.database.overwatch

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

data class OverwatchHerosStats(
        @field:SerializedName("stats") val heroesGameStats: OverwatchStats<Map<String, OverwatchHeroStats>>,
        @field:SerializedName("playtime") val heroesPlaytimeStats: OverwatchStats<Map<String, String>>
)