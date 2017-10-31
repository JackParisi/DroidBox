package com.github.jackparisi.droidboxsample.database.overwatch

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

data class OverwatchPlayerStats(
        @field:SerializedName("average_stats") val averageStats: Map<String, String>,
        @field:SerializedName("rolling_average_stats") val rollingAverageStats: Map<String, String>,
        @field:SerializedName("overall_stats") val overallStats: Map<String, String>,
        @field:SerializedName("game_stats") val gameStats: Map<String, String>,
        @field:SerializedName("competitive") val competitive: Boolean
)