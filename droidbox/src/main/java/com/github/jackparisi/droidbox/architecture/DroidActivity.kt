package com.github.jackparisi.droidbox.architecture

import android.support.v7.app.AppCompatActivity
import com.github.jackparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

abstract class DroidActivity<out W : DroidWrapperService> : AppCompatActivity() {

    // Wrapper for build Activity view
    abstract protected val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }
}