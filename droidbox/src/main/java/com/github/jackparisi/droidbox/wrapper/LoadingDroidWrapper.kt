package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.databinding.Observable
import android.view.View
import android.view.ViewGroup
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

    override fun wrapLayout(viewModel: DroidViewModel, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams): View {
        val frameLayout = FrameLayout(context)
        frameLayout.layoutParams = params
        frameLayout.addView(pageLayout, params)
        this.viewModel = viewModel

        if(wrapperLayout != null) {
            frameLayout.addView(wrapperLayout, params)

            loadingCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    wrapperLayout.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
                }
            }

            viewModel.loading.addOnPropertyChangedCallback(loadingCallback)


            wrapperLayout.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
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