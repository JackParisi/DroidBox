package com.github.giacomoparisi.droidbox.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 07/07/2017.
 * https://github.com/giacomoParisi
 */

/**
 *
 * The recyclerView.Adapter that populate the recyclerView with DroidItems
 * and bind them with the data.
 * The adapter support the following features:
 *
 * - Android data binding
 * - Different DroidItem (with different DroidViewHolder.Factory)
 *
 * @param itemList The list of DroidItem for populate the recyclerView
 * @param layoutInflater The layoutInflater reference for layout inflating
 * @param viewModel The viewModel of the view that hold the recyclerView
 */
open class DroidAdapter(
        private var itemList: List<DroidItem>,
        private val layoutInflater: LayoutInflater,
        private val viewModel: DroidViewModel? = null)
    : androidx.recyclerview.widget.RecyclerView.Adapter<DroidViewHolder<*>>() {

    // Map of the all DroidViewHolder.Factory for the different DroidItems
    private var viewHolderMap: MutableMap<Int, DroidViewHolder.Factory<*,*>> = mutableMapOf()

    override fun onBindViewHolder(holder: DroidViewHolder<*>, position: Int) {
        holder.globalPosition.set(position)
        holder.bind(itemList[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DroidViewHolder<*> {
        val viewHolder = viewHolderMap[viewType]!!.create(layoutInflater, parent)
        viewModel.let { viewHolder.viewModel = viewModel}
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {

        val viewHolderlayoutId = itemList[position].getItemViewHolder().layoutId

        for ((key, value) in viewHolderMap) {
            if (value.layoutId == viewHolderlayoutId) {
                return key
            }
        }

        viewHolderMap[viewHolderMap.size + 1] = itemList[position].getItemViewHolder()
        return viewHolderMap.size
    }
}
