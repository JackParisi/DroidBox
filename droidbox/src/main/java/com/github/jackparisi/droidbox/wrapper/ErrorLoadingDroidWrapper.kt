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
class ErrorLoadingDroidWrapper : DroidWrapper() {

    private var loadingCallback: Observable.OnPropertyChangedCallback? = null
    private var errorCallback: Observable.OnPropertyChangedCallback? = null
    private lateinit var viewModels: List<DroidViewModel>

    override fun wrapLayout(viewModels: List<DroidViewModel>, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams): View {

        val frameLayout = FrameLayout(context)
        frameLayout.addView(pageLayout, params)

        this.viewModels = viewModels

        if(wrapperLayout != null) {
            frameLayout.addView(wrapperLayout, params)

            loadingCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    wrapperLayout.visibility = if (shouldShowWrapper()) View.GONE else View.VISIBLE
                }
            }

            for(viewModel in viewModels) {
                viewModel.loading.addOnPropertyChangedCallback(loadingCallback)
            }

            errorCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    wrapperLayout.visibility = if (shouldShowWrapper()) View.GONE else View.VISIBLE
                }
            }

            for (viewModel in viewModels) {
                viewModel.error.addOnPropertyChangedCallback(errorCallback)
            }


            wrapperLayout.visibility = if (shouldShowWrapper()) View.GONE else View.VISIBLE
        }else{
            //TODO throw exception
            Timber.e("Error Loading Wrapper is null")
        }

        return frameLayout
    }

    private fun shouldShowWrapper(): Boolean{

        return viewModels.any { it.loading.get() || it.error.get() }
    }

    fun removeCallbacks() {
        for(viewModel in viewModels) {
            viewModel.loading.removeOnPropertyChangedCallback(loadingCallback)
            viewModel.error.removeOnPropertyChangedCallback(errorCallback)
        }
    }
}
