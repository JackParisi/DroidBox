package com.github.jackparisi.droidbox.architecture.model.error

import android.databinding.Observable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.jackparisi.droidbox.architecture.model.DroidBoxViewModel

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
class ErrorLoadingWrapper {

    private var loadingCallback: Observable.OnPropertyChangedCallback? = null
    private var errorCallback: Observable.OnPropertyChangedCallback? = null
    private var viewModel: DroidBoxViewModel? = null


    fun wrapLayout(viewModel: DroidBoxViewModel, root: View, wrapperRoot: ViewGroup): View {
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        return wrapLayout(viewModel, root, wrapperRoot, params)
    }

    fun wrapLayout(viewModel: DroidBoxViewModel, root: View, wrapperRoot: ViewGroup, params: FrameLayout.LayoutParams): View {
        this.viewModel = viewModel
        wrapperRoot.addView(root, params)

        loadingCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                root.visibility = if (viewModel.loading.get()) View.GONE else View.VISIBLE
            }
        }
        viewModel.loading.addOnPropertyChangedCallback(loadingCallback)

        errorCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                root.visibility = if (viewModel.error.get()) View.GONE else View.VISIBLE
            }
        }
        viewModel.error.addOnPropertyChangedCallback(errorCallback)


        root.visibility = if (viewModel.loading.get() || viewModel.error.get()) View.GONE else View.VISIBLE

        return wrapperRoot
    }

    fun removeCallbacks() {
        viewModel!!.loading.removeOnPropertyChangedCallback(loadingCallback)
        viewModel!!.error.removeOnPropertyChangedCallback(errorCallback)
    }
}
