package com.github.jackparisi.droidboxsample.database.games

import android.databinding.ObservableField
import com.github.jackparisi.droidbox.recycler.DroidItem
import com.github.jackparisi.droidbox.recycler.sections.DroidSectionView
import com.github.jackparisi.droidboxsample.databinding.SectionGameBinding

/**
 * Created by Giacomo Parisi on 26/10/17.
 * https://github.com/JackParisi
 */

class GameSection(private val bindingView: SectionGameBinding) : DroidSectionView(bindingView.root) {

    val data = ObservableField<Game>()

    init {
        bindingView.section = this
    }

    override fun updateSection(droidItem: DroidItem) {
        if (droidItem is Game) {
            this.data.set(droidItem)
            bindingView.executePendingBindings()
        }
    }
}