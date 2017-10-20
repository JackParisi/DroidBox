package com.github.jackparisi.droidbox.wrapper

import android.databinding.Observable
import android.view.View
import android.widget.FrameLayout
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import timber.log.Timber

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

class ErrorDroidWrapper : DroidWrapper() {

    private var errorCallback: Observable.OnPropertyChangedCallback? = null
    private var viewModel: DroidViewModel? = null


    override fun <T : DroidWrapperSettings> wrapLayout(settings: T): View {

        val frameLayout = FrameLayout(settings.context)
        frameLayout.addView(settings.pageLayout, settings.params)

        this.viewModel = viewModel

        if (settings.wrapperLayout != null) {
            frameLayout.addView(settings.wrapperLayout, settings.params)

            errorCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    settings.wrapperLayout.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
                }
            }

            viewModel?.error?.addOnPropertyChangedCallback(errorCallback)


            settings.wrapperLayout.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
        }else{
            //TODO throw exception
            Timber.e("Error Loading Wrapper is null")
        }

        return frameLayout
    }

    private fun shouldShowWrapper(): Boolean{

        return viewModel?.error!!.get()
    }

    fun removeCallback() {
        viewModel?.error?.removeOnPropertyChangedCallback(errorCallback)
    }
}
