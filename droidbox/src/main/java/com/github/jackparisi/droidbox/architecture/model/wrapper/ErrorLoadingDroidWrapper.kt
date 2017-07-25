package com.github.jackparisi.droidbox.architecture.model.wrapper

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

    override fun wrapLayout(viewModel: DroidViewModel, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams): View {

        val frameLayout = FrameLayout(context)
        frameLayout.addView(pageLayout, params)

        if(wrapperLayout != null) {
            frameLayout.addView(wrapperLayout, params)

            this.viewModel = viewModel

            loadingCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    pageLayout.visibility = if (viewModel.loading.get()) View.GONE else View.VISIBLE
                }
            }
            viewModel.loading.addOnPropertyChangedCallback(loadingCallback)

            errorCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, i: Int) {
                    wrapperLayout.visibility = if (viewModel.error.get()) View.GONE else View.VISIBLE
                }
            }
            viewModel.error.addOnPropertyChangedCallback(errorCallback)


            wrapperLayout.visibility = if (viewModel.loading.get() || viewModel.error.get()) View.GONE else View.VISIBLE
        }else{
            //TODO throw exception
            Timber.e("Error Loading Wrapper is null")
        }

        return frameLayout
    }

    fun removeCallbacks() {
        viewModel!!.loading.removeOnPropertyChangedCallback(loadingCallback)
        viewModel!!.error.removeOnPropertyChangedCallback(errorCallback)
    }
}
