package com.github.jackparisi.droidboxsample.database.overwatch

import com.google.gson.annotations.SerializedName

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

data class OverwatchStats<out T>(
        @field:SerializedName("quickplay") val quickPlay: T,
        @field:SerializedName("competitive") val competitive: T
)