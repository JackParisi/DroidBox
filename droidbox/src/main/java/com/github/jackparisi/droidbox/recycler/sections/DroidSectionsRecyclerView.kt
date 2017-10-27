package com.github.jackparisi.droidbox.recycler.sections

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import com.github.jackparisi.droidbox.recycler.DroidAdapter
import com.github.jackparisi.droidbox.recycler.DroidItem
import com.github.jackparisi.droidbox.recycler.sections.DroidSectionView.Companion.UPDATES_WHEN_ITEMS_DISAPPEARS_COMPLETELY
import com.github.jackparisi.droidbox.recycler.sections.DroidSectionView.Companion.UPDATES_WHEN_ITEMS_DISAPPEARS_PARTIALLY
import com.github.jackparisi.droidbox.wrapper.DroidWrapperSettings

/**
 * Created by Giacomo Parisi on 26/10/17.
 * https://github.com/JackParisi
 */

/**
 *
 * Special recycler view that allow to put a section view on top, and update it
 * based on first visible element
 */
class DroidSectionsRecyclerView : LinearLayout {

    private var recyclerView: RecyclerView? = null
    private var sectionView: DroidSectionView? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        orientation = LinearLayout.VERTICAL
    }

    fun initializeSections(sectionView: DroidSectionView, layoutManager: LinearLayoutManager, list: List<DroidItem>, adapter: DroidAdapter) {

        this.removeAllViews()

        this@DroidSectionsRecyclerView.sectionView = sectionView
        addView(
                this@DroidSectionsRecyclerView.sectionView!!.view,
                this@DroidSectionsRecyclerView.sectionView!!.layoutParams
        )
        recyclerView = RecyclerView(context)
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleIndex =
                        when (this@DroidSectionsRecyclerView.sectionView!!.updateType) {
                            UPDATES_WHEN_ITEMS_DISAPPEARS_PARTIALLY ->
                                layoutManager.findFirstCompletelyVisibleItemPosition()
                            UPDATES_WHEN_ITEMS_DISAPPEARS_COMPLETELY ->
                                layoutManager.findFirstVisibleItemPosition()
                            else -> -1
                        }
                if (list.getOrNull(firstVisibleIndex) != null) {
                    this@DroidSectionsRecyclerView.sectionView!!.updateSection(list[firstVisibleIndex])
                }
            }
        })
        recyclerView!!.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
        addView(recyclerView, DroidWrapperSettings.getMatchParentParams())

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
    }
}