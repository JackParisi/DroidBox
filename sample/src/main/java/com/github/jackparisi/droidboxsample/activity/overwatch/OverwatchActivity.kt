package com.github.jackparisi.droidboxsample.activity.overwatch

import android.os.Bundle
import com.github.jackparisi.droidbox.delegate.viewModelProvider
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleActivity
import com.github.jackparisi.droidboxsample.core.component

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

class OverwatchActivity : DroidBoxSampleActivity() {

    private val viewModel by viewModelProvider { component.overwatchViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadData()
    }
}