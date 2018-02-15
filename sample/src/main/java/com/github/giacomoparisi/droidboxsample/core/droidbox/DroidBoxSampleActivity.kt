package com.github.giacomoparisi.droidboxsample.core.droidbox

import com.github.giacomoparisi.droidbox.architecture.DroidActivity

/**
 * Created by Giacomo Parisi on 14/12/17.
 * https://github.com/giacomoParisi
 */
abstract class DroidBoxSampleActivity : DroidActivity<AppWrapper>() {

    override val wrapper: AppWrapper
        get() = AppWrapper(this)
}
