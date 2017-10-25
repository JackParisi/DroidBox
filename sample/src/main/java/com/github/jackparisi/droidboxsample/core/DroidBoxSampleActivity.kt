package com.github.jackparisi.droidboxsample.core

import com.github.jackparisi.droidbox.architecture.DroidActivity

/**
 * Created by Giacomo Parisi on 25/10/17.
 * https://github.com/JackParisi
 */

abstract class DroidBoxSampleActivity : DroidActivity<DroidBoxSampleWrapper>() {

    override val wrapper: DroidBoxSampleWrapper
        get() = DroidBoxSampleWrapper(this)

}