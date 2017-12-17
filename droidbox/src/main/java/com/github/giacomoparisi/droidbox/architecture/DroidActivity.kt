package com.github.giacomoparisi.droidbox.architecture

import android.support.v7.app.AppCompatActivity
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidActivity<out W : DroidWrapperService> : AppCompatActivity() {

    // Wrapper for build Activity view
    abstract protected val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }
}