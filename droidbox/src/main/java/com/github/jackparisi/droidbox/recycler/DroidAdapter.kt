package com.github.jackparisi.droidbox.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 07/07/2017.
 * https://github.com/JackParisi
 */

class DroidAdapter(
        var itemList: List<DroidItem>,
        val layoutInflater: LayoutInflater,
        val viewModel: DroidViewModel? = null)
    : RecyclerView.Adapter<DroidViewHolder>() {

    var viewHolderMap: MutableMap<Int, DroidViewHolder.Factory<*>> = mutableMapOf()

    override fun onBindViewHolder(holder: DroidViewHolder?, position: Int) {
        holder?.globalPosition?.set(position)
        holder?.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DroidViewHolder {
        val viewHolder = viewHolderMap[viewType]!!.create(layoutInflater, parent!!)
        viewModel.let { viewHolder.viewModel = viewModel}
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {

        val viewHolderClassName = itemList[position].getItemViewHolder().javaClass.name

        for ((key, value) in viewHolderMap) {
            if (value.javaClass.name == viewHolderClassName) {
                return key
            }
        }

        viewHolderMap.put(viewHolderMap.size + 1, itemList[position].getItemViewHolder())
        return viewHolderMap.size
    }
}
