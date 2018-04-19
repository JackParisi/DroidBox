package com.github.giacomoparisi.droidbox.recycler

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 10/04/17.
 * https://github.com/giacomoParisi
 */

/**
 *
 * RecyclerView ViewHolder that support the DroidBox architecture
 */
abstract class DroidViewHolder<T : DroidItem>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    // DroidViewModel of the page that menage the recyclerView
    var viewModel: DroidViewModel? = null

    // The global position of the item in the list
    var globalPosition = ObservableInt()

    // The local position of the item in the list
    var localPosition = ObservableInt()

    // The droid item that this viewHolder bind
    val data = ObservableField<T>()

    /**
     *
     * Bind the viewHolder with the specific data of an DroidItem
     *
     * @param data The droidItem to bind with the viewHolder
     */
    @Suppress("UNCHECKED_CAST")
    open fun bind(data: DroidItem) {
        try {
            this.data.set(data as T)
        } catch (excetpion: RuntimeException) {
        }
    }


    /**
     *
     * Factory class to setup the DroidViewHolder
     *
     * @param layoutId The layout id of the view that the viewHolder menage
     * @param factory The ViewHolderFatctory to build the viewHolder
     */
    class Factory<B : ViewDataBinding, D : DroidItem>(
            val layoutId: Int,
            private val factory: ViewHolderFactory<B, D>) {

        fun create(inflater: LayoutInflater, parent: ViewGroup): DroidViewHolder<D> {
            val binding = DataBindingUtil.inflate<B>(
                    inflater,
                    layoutId,
                    parent,
                    false)
            return factory.newInstance(binding)
        }
    }
}
