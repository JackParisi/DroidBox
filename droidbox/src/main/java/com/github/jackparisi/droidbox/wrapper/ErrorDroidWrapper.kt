package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.databinding.Observable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import timber.log.Timber

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

class ErrorDroidWrapper : DroidWrapper() {

    private var errorCallback: Observable.OnPropertyChangedCallback? = null
    private lateinit var viewModel: DroidViewModel


    override fun wrapLayout(viewModel: DroidViewModel, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams): View {

        val frameLayout = FrameLayout(context)
        frameLayout.addView(pageLayout, params)

        this.viewModel = viewModel

        if(wrapperLayout != null) {
            frameLayout.addView(wrapperLayout, params)

            errorCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    wrapperLayout.visibility = if (shouldShowWrapper()) View.GONE else View.VISIBLE
                }
            }

            viewModel.error.addOnPropertyChangedCallback(errorCallback)


            wrapperLayout.visibility = if (shouldShowWrapper()) View.GONE else View.VISIBLE
        }else{
            //TODO throw exception
            Timber.e("Error Loading Wrapper is null")
        }

        return frameLayout
    }

    private fun shouldShowWrapper(): Boolean{

        return viewModel.loading.get()
    }

    fun removeCallback() {
        viewModel.error.removeOnPropertyChangedCallback(errorCallback)
    }
}
