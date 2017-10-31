package com.github.jackparisi.droidboxsample.database.games

import com.github.jackparisi.droidbox.recycler.DroidItem
import com.github.jackparisi.droidbox.recycler.DroidViewHolder
import com.github.jackparisi.droidbox.recycler.ViewHolderFactory
import com.github.jackparisi.droidboxsample.R
import com.github.jackparisi.droidboxsample.databinding.ItemGameBinding

/**
 * Created by Giacomo Parisi on 25/10/17.
 * https://github.com/JackParisi
 */

class Game(
        val id: Int,
        val name: String,
        val banner: Int
) : DroidItem {

    override fun getItemViewHolder(): DroidViewHolder.Factory<*> =
            DroidViewHolder.Factory(
                    R.layout.item_game,
                    object : ViewHolderFactory<ItemGameBinding> {

                        override fun newInstance(binding: ItemGameBinding): DroidViewHolder = GameViewHolder(binding)
                    }
            )
}