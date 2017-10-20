package com.github.jackparisi.droidbox.wrapper

import android.view.View

/**
 * Created by Giacomo Parisi on 25/07/17.
 * https://github.com/JackParisi
 */

/**
 *
 * Wrapper class that allows to combine 2 layouts (specific page layout with a common layout)
 */
abstract class DroidWrapper {
    /**
     *
     * Wrap the page layout with the wrapper layout using the settings
     *
     * @param T The droidWrapperSettings needed to preform the wrap
     *
     * @return Return the view that wrap the page with the wrapper layout
     */
    abstract fun <T : DroidWrapperSettings> wrapLayout(settings: T): View
}