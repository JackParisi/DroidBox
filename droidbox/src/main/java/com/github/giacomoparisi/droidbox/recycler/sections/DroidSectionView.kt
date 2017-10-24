package com.github.giacomoparisi.droidbox.recycler.sections

import android.view.View
import android.view.ViewGroup
import com.github.giacomoparisi.droidbox.recycler.DroidItem
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperSettings

/**
 * Created by Giacomo Parisi on 26/10/17.
 * https://github.com/giacomoParisi
 */

abstract class DroidSectionView(
        val view: View,
        val layoutParams: ViewGroup.LayoutParams = DroidWrapperSettings.getMatchWrapParams(),
        val updateType: Int = UPDATES_WHEN_ITEMS_DISAPPEARS_COMPLETELY
) {

    abstract fun updateSection(droidItem: DroidItem)

    companion object {
        val UPDATES_WHEN_ITEMS_DISAPPEARS_PARTIALLY = 1
        val UPDATES_WHEN_ITEMS_DISAPPEARS_COMPLETELY = 2
    }
}
