package com.github.jackparisi.droidboxsample.database.overwatch

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */
data class OverwatchStatsResponse(
        @field:SerializedName("eu") val eu: OverwatchCountryStats,
        @field:SerializedName("kr") val kr: OverwatchCountryStats,
        @field:SerializedName("us") val us: OverwatchCountryStats
)