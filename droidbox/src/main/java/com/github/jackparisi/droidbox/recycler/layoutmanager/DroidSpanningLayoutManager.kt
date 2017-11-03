package com.github.jackparisi.droidbox.recycler.layoutmanager

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * Created by Giacomo Parisi on 03/11/17.
 * https://github.com/JackParisi
 */

class DroidSpanningLayoutManager : LinearLayoutManager {

    private val horizontalSpace: Int
        get() = width - paddingRight - paddingLeft

    private val verticalSpace: Int
        get() = height - paddingBottom - paddingTop

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams = spanLayoutSize(super.generateDefaultLayoutParams())

    override fun generateLayoutParams(c: Context, attrs: AttributeSet): RecyclerView.LayoutParams = spanLayoutSize(super.generateLayoutParams(c, attrs))

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams): RecyclerView.LayoutParams = spanLayoutSize(super.generateLayoutParams(lp))

    private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            layoutParams.width = Math.round(horizontalSpace / itemCount.toDouble()).toInt()
        } else if (orientation == LinearLayoutManager.VERTICAL) {
            layoutParams.height = Math.round(verticalSpace / itemCount.toDouble()).toInt()
        }
        return layoutParams
    }

    override fun canScrollVertically(): Boolean = false

    override fun canScrollHorizontally(): Boolean = false
}