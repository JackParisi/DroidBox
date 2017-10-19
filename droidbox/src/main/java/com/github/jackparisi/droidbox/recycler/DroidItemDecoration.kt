package com.github.jackparisi.droidbox.recycler

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.jackparisi.droidbox.utility.dpToPx

/**
 * Created by Giacomo Parisi on 19/10/17.
 * https://github.com/JackParisi
 */
class DroidItemDecoration(
        private val context: Context,
        private val listSize: Int) : RecyclerView.ItemDecoration() {

    var leftMarginDp: Float = 0f
        set(value) {
            dpToPx(value, context)
        }
    var rightMarginDp: Float = 0f
        set(value) {
            dpToPx(value, context)
        }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        if (outRect != null) {
            outRect.left = leftMarginDp.toInt()
        }
        if (outRect != null) {
            outRect.right = rightMarginDp.toInt()
        }
    }
}