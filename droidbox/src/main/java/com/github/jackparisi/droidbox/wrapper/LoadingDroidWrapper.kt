package com.github.jackparisi.droidbox.wrapper

import android.databinding.Observable
import android.view.View
import android.widget.FrameLayout
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import timber.log.Timber

/**
 * Created by Giacomo Parisi on 30/07/17.
 * https://github.com/JackParisi
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
        frameLayout.layoutParams = settings.params
        frameLayout.addView(settings.pageLayout, settings.params)
        this.viewModel = settings.viewModel

        if (settings.wrapperLayout != null) {
            frameLayout.addView(settings.wrapperLayout, settings.params)

            loadingCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    settings.wrapperLayout.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
                }
            }

            viewModel?.loading?.addOnPropertyChangedCallback(loadingCallback)


            settings.wrapperLayout.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
        }else{
            //TODO throw exception
            Timber.e("Error Loading Wrapper is null")
        }

        return frameLayout
    }

    /**
     *
     * Return true if the loading view is needed false for hide it
     *
     * @return True if the loading view is needed
     */
    private fun shouldShowWrapper(): Boolean{

        return viewModel?.loading!!.get()
    }

    /**
     *
     * Remove the binding to the droidViewModel's loading field
     */
    fun removeCallback() {
        viewModel?.loading?.removeOnPropertyChangedCallback(loadingCallback)
    }
}