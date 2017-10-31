package com.github.jackparisi.droidboxsample.database.overwatch

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

data class OverwatchCountryStats(
        @field:SerializedName("stats") val playerStats: OverwatchStats<OverwatchPlayerStats>,
        @field:SerializedName("heroes") val heroesStats: OverwatchHerosStats
)