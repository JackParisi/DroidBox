package com.github.giacomoparisi.droidbox.wrapper.toolbar

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapper
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperSettings


/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

/**
 *
 * DroidWrapper for toolbar.
 * Put the page layout below the toolbar using a linearLayout
 */
class ToolbarDroidWrapper : DroidWrapper() {

    override fun <T : DroidWrapperSettings> wrapLayout(settings: T): View {
        if (settings is ToolbarDroidSettings) {
            return if (settings.overPageLayout) {
                wrapOverPageLayout(settings)
            } else {
                val linearLayout = LinearLayout(settings.context)
                linearLayout.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                linearLayout.orientation = LinearLayout.VERTICAL
                if (settings.wrapperLayout != null) {
                    linearLayout.addView(settings.wrapperLayout.view, settings.wrapperLayout.layoutParams)
                }

                linearLayout.addView(settings.pageLayout.view, settings.pageLayout.layoutParams)

                linearLayout
            }
        }

        return settings.pageLayout.view
    }

    private fun wrapOverPageLayout(settings: ToolbarDroidSettings): View {
        val frameLayout = FrameLayout(settings.context)
        frameLayout.addView(settings.pageLayout.view, settings.pageLayout.layoutParams)
        if (settings.wrapperLayout != null) {
            frameLayout.addView(settings.wrapperLayout.view, settings.wrapperLayout.layoutParams)

            if (settings.pushDownContentAtIndex >= 0) {
                settings.wrapperLayout.view.viewTreeObserver?.addOnGlobalLayoutListener(
                        object : ViewTreeObserver.OnGlobalLayoutListener {

                            override fun onGlobalLayout() {
                                settings.wrapperLayout.view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                                val height = settings.wrapperLayout.view.measuredHeight

                                if (settings.pageLayout.view is ViewGroup) {
                                    if (settings.pageLayout.view.childCount > 0) {
                                        val view = settings.pageLayout.view.getChildAt(settings.pushDownContentAtIndex)
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
            }
        }
        return frameLayout
    }
}
