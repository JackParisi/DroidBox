package com.github.jackparisi.droidbox.recycler.sections

import android.view.View
import com.github.jackparisi.droidbox.recycler.DroidItem

/**
 * Created by Giacomo Parisi on 26/10/17.
 * https://github.com/JackParisi
 */

abstract class DroidSectionView(
        val view: View,
        val updateType: Int = UPDATES_WHEN_ITEMS_DISAPPERARS_COMPLETELY
) {

    abstract fun updateSection(droidItem: DroidItem)

    companion object {
        val UPDATES_WHEN_ITEMS_DISAPPERARS_PARTIALLY = 1
        val UPDATES_WHEN_ITEMS_DISAPPERARS_COMPLETELY = 2
    }
}
