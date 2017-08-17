package com.github.jackparisi.droidbox.recycler

import android.databinding.DataBindingUtil
import android.databinding.ObservableInt
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 10/04/17.
 * https://github.com/JackParisi
 */

abstract class DroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var viewModel: DroidViewModel? = null

    var globalPosition = ObservableInt()

    var localPosition = ObservableInt()

    abstract fun bind(data: DroidItem)


    class Factory<B : ViewDataBinding>(private val layoutId: Int, private val factory: ViewHolderFactory<B>) {

        fun create(inflater: LayoutInflater, parent: ViewGroup): DroidViewHolder {
            val binding = DataBindingUtil.inflate<B>(inflater, layoutId, parent, false)
            return factory.newInstance(binding)
        }
    }
}
