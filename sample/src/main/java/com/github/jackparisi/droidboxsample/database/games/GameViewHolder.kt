package com.github.jackparisi.droidboxsample.database.games

import android.databinding.ObservableField
import com.github.jackparisi.droidbox.recycler.DroidItem
import com.github.jackparisi.droidbox.recycler.DroidViewHolder
import com.github.jackparisi.droidboxsample.databinding.ItemGameBinding

/**
 * Created by Giacomo Parisi on 26/10/17.
 * https://github.com/JackParisi
 */

class GameViewHolder(private val bindingView: ItemGameBinding) : DroidViewHolder(bindingView.root) {

    val data = ObservableField<Game>()

    init {
        bindingView.viewHolder = this
    }

    override fun bind(data: DroidItem) {
        if (data is Game) {
            data.getBannerUrl()
            this.data.set(data)
            bindingView.executePendingBindings()
        }
    }
}