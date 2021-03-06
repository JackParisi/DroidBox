package com.github.giacomoparisi.droidbox.recycler

import androidx.databinding.ViewDataBinding

/**
 * Created by Giacomo Parisi on 10/04/17.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Factory class used to create a DroidViewHolder for a specifc item
 * Every DroidItem need this
 */
interface ViewHolderFactory<in B : ViewDataBinding, D: DroidItem> {

    /**
     *
     * Create a DroidViewHolder instance for the specific item ViewDataBinding
     *
     * @param binding The ViewDataBinding object of the DroidItem
     */
    fun newInstance(binding: B): DroidViewHolder<D>
}
