package com.github.jackparisi.droidbox.wrapper.toolbar

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.github.jackparisi.droidbox.wrapper.DroidWrapper
import com.github.jackparisi.droidbox.wrapper.DroidWrapperSettings


/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

/**
 *
 * DroidWrapper for toolbar.
 * Put the page layout below the toolbar using a linearLayout
 */
class ToolbarDroidWrapper : DroidWrapper() {

    override fun <T : DroidWrapperSettings> wrapLayout(settings: T): View {
        if (settings is ToolbarDroidSettings) {
            if (settings.overPageLayout) {
                val frameLayout = FrameLayout(settings.context)
                frameLayout.addView(settings.pageLayout.view, settings.pageLayout.layoutParams)
                frameLayout.addView(settings.wrapperLayout.view, settings.wrapperLayout.layoutParams)
                settings.wrapperLayout.view.viewTreeObserver?.addOnGlobalLayoutListener(
                        object : ViewTreeObserver.OnGlobalLayoutListener {

                            override fun onGlobalLayout() {
                                settings.wrapperLayout.view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                                val height = settings.wrapperLayout.view.measuredHeight

                                if (settings.pageLayout.view is ViewGroup) {
                                    if (settings.pageLayout.view.childCount > 0) {
                                        val view = settings.pageLayout.view.getChildAt(0)
                                        val params = view.layoutParams
                                        if (params is ViewGroup.MarginLayoutParams) {
                                            params.topMargin = params.topMargin + height
                                            view.layoutParams = params
                                        }
                                    }
                                }
                            }
                        }
                )
                return frameLayout
            } else {
                val linearLayout = LinearLayout(settings.context)
                linearLayout.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                linearLayout.orientation = LinearLayout.VERTICAL
                linearLayout.addView(settings.wrapperLayout.view, settings.wrapperLayout.layoutParams)

                linearLayout.addView(settings.pageLayout.view, settings.pageLayout.layoutParams)

                return linearLayout
            }
        }

        return settings.pageLayout.view
    }
}
