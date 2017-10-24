package com.github.giacomoparisi.droidbox.recycler

/**
 * Created by Giacomo Parisi on 07/07/2017.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Interface that represent an item for the recyclerView.
 * Every item need to implement for the DroidAdapter
 */
interface DroidItem {

    /**
     *
     * Method that return the specific DroidViewHolder.Factory for the item
     */
    fun getItemViewHolder(): DroidViewHolder.Factory<*>
}
