package com.github.jackparisi.droidbox.architecture

import android.app.Activity
import com.github.jackparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidActivity<out W : DroidWrapperService> : Activity() {

    abstract protected val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }
}