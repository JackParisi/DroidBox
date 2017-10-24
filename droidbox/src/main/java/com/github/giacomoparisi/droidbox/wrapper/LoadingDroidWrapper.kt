package com.github.giacomoparisi.droidbox.wrapper

import android.databinding.Observable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 30/07/17.
 * https://github.com/giacomoParisi
 */

/**
 *
 * DroidWrapper for loading layout.
 * Put the page and the loading layout inside a frame layout with the loading on top
 * and bind the visibility with the droidViewModel's loading field
 */
class LoadingDroidWrapper : DroidWrapper() {

    // Callback for droidViewModel's loading field, fired every time the property value change
    private var loadingCallback: Observable.OnPropertyChangedCallback? = null

    // Reference of the droidViewModel of the page
    private var viewModel: DroidViewModel? = null

    override fun <T : DroidWrapperSettings> wrapLayout(settings: T): View {
        val frameLayout = FrameLayout(settings.context)
        frameLayout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        frameLayout.addView(settings.pageLayout.view, settings.pageLayout.layoutParams)
        this.viewModel = settings.viewModel

        if (settings.wrapperLayout != null) {
            frameLayout.addView(settings.wrapperLayout.view, settings.wrapperLayout.layoutParams)

            loadingCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    settings.wrapperLayout.view.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
                }
            }

            viewModel?.uiManager?.loading?.addOnPropertyChangedCallback(loadingCallback)


            settings.wrapperLayout.view.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE

        }
        return frameLayout
    }

    /**
     *
     * Return true if the loading view is needed false for hide it
     *
     * @return True if the loading view is needed
     */
    private fun shouldShowWrapper(): Boolean {

        return viewModel?.uiManager?.loading!!.get()
    }

    /**
     *
     * Remove the binding to the droidViewModel's loading field
     */
    fun removeCallback() {
        viewModel?.uiManager?.loading?.removeOnPropertyChangedCallback(loadingCallback)
    }
}