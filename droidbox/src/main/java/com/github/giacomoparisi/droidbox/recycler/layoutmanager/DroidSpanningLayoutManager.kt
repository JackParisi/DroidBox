package com.github.giacomoparisi.droidbox.recycler.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * Created by Giacomo Parisi on 03/11/17.
 * https://github.com/giacomoParisi
 */

class DroidSpanningLayoutManager : androidx.recyclerview.widget.LinearLayoutManager {

    private val horizontalSpace: Int
        get() = width - paddingRight - paddingLeft

    private val verticalSpace: Int
        get() = height - paddingBottom - paddingTop

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun generateDefaultLayoutParams(): androidx.recyclerview.widget.RecyclerView.LayoutParams = spanLayoutSize(super.generateDefaultLayoutParams())

    override fun generateLayoutParams(c: Context, attrs: AttributeSet): androidx.recyclerview.widget.RecyclerView.LayoutParams = spanLayoutSize(super.generateLayoutParams(c, attrs))

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams): androidx.recyclerview.widget.RecyclerView.LayoutParams = spanLayoutSize(super.generateLayoutParams(lp))

    private fun spanLayoutSize(layoutParams: androidx.recyclerview.widget.RecyclerView.LayoutParams): androidx.recyclerview.widget.RecyclerView.LayoutParams {
        if (orientation == androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL) {
            layoutParams.width = Math.round(horizontalSpace / itemCount.toDouble()).toInt()
        } else if (orientation == androidx.recyclerview.widget.LinearLayoutManager.VERTICAL) {
            layoutParams.height = Math.round(verticalSpace / itemCount.toDouble()).toInt()
        }
        return layoutParams
    }

    override fun canScrollVertically(): Boolean = false

    override fun canScrollHorizontally(): Boolean = false
}