package com.github.jackparisi.droidbox.recycler

/**
 * Created by Giacomo Parisi on 07/07/2017.
 * https://github.com/JackParisi
 */

interface DroidItem {

    fun getItemViewHolder(): DroidViewHolder.Factory<*>
}
