package com.github.jackparisi.droidboxsample.database.overwatch

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

data class OverwatchHeroStats(
        @field:SerializedName("average_stats") val averageStats: Map<String, String>,
        @field:SerializedName("rolling_average_stats") val rollingAverageStats: Map<String, String>,
        @field:SerializedName("general_stats") val generalStats: Map<String, String>,
        @field:SerializedName("hero_stats") val heroStats: Map<String, String>
)