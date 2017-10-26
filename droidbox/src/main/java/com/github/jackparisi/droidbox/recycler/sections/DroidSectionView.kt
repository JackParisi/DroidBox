package com.github.jackparisi.droidbox.recycler.sections

import android.view.View
import com.github.jackparisi.droidbox.recycler.DroidItem

/**
 * Created by Giacomo Parisi on 26/10/17.
 * https://github.com/JackParisi
 */

abstract class DroidSectionView(
        val view: View
) {

    abstract fun updateSection(droidItem: DroidItem)
}