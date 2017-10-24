package com.github.giacomoparisi.droidbox.architecture

import android.support.v4.app.Fragment
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidFragment<out W : DroidWrapperService> : Fragment() {

    // Wrapper for build Fragment view
    abstract protected val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }
}
