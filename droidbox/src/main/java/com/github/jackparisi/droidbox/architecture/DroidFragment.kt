package com.github.jackparisi.droidbox.architecture

import android.app.Fragment
import com.github.jackparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidFragment<out W : DroidWrapperService> : Fragment() {

    // Wrapper for build Fragment view
    abstract protected val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }
}
