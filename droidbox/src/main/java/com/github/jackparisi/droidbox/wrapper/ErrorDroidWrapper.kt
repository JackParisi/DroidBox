package com.github.jackparisi.droidbox.wrapper

import android.databinding.Observable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

class ErrorDroidWrapper : DroidWrapper() {

    private var errorCallback: Observable.OnPropertyChangedCallback? = null
    private var viewModel: DroidViewModel? = null


    override fun <T : DroidWrapperSettings> wrapLayout(settings: T): View {

        val frameLayout = FrameLayout(settings.context)
        frameLayout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        frameLayout.addView(settings.pageLayout.view, settings.pageLayout.layoutParams)

        this.viewModel = settings.viewModel


        frameLayout.addView(settings.wrapperLayout.view, settings.wrapperLayout.layoutParams)

            errorCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    settings.wrapperLayout.view.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE
                }
            }

            viewModel?.error?.addOnPropertyChangedCallback(errorCallback)


        settings.wrapperLayout.view.visibility = if (shouldShowWrapper()) View.VISIBLE else View.GONE

        return frameLayout
    }

    private fun shouldShowWrapper(): Boolean{

        return viewModel?.error!!.get()
    }

    fun removeCallback() {
        viewModel?.error?.removeOnPropertyChangedCallback(errorCallback)
    }
}
