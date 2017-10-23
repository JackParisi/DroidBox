package com.github.jackparisi.droidbox.wrapper.toolbar

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.github.jackparisi.droidbox.wrapper.DroidWrapper
import com.github.jackparisi.droidbox.wrapper.DroidWrapperSettings
import timber.log.Timber


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
                if (settings.wrapperLayout != null) {
                    frameLayout.addView(settings.pageLayout, settings.params)
                } else {
                    //TODO throw exception
                    Timber.e("Toolbar wrapper is null")
                }
                frameLayout.addView(settings.wrapperLayout)
                settings.wrapperLayout?.viewTreeObserver?.addOnGlobalLayoutListener(
                        object : ViewTreeObserver.OnGlobalLayoutListener {
                            override fun onGlobalLayout() {
                                settings.wrapperLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                                val height = settings.wrapperLayout.measuredHeight

                                if (settings.pageLayout is ViewGroup) {
                                    if (settings.pageLayout.childCount > 0) {
                                        val params = settings.pageLayout.getChildAt(0).layoutParams
                                        if (params is ViewGroup.MarginLayoutParams) {
                                            params.topMargin = params.topMargin + height
                                            settings.pageLayout.layoutParams = params
                                        }
                                    }

                                }
                            }

                        }
                )

                return frameLayout
            } else {
                val linearLayout = LinearLayout(settings.context)
                linearLayout.orientation = LinearLayout.VERTICAL
                if (settings.wrapperLayout != null) {
                    linearLayout.addView(settings.wrapperLayout)
                } else {
                    //TODO throw exception
                    Timber.e("Toolbar wrapper is null")
                }
                linearLayout.addView(settings.pageLayout, settings.params)

                return linearLayout
            }
        }

        return settings.pageLayout
    }
}
