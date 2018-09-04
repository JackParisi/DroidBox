package com.github.giacomoparisi.droidbox.architecture

import androidx.fragment.app.Fragment
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidView
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidFragment<out W : DroidWrapperService> : androidx.fragment.app.Fragment(), DroidView {

    // Wrapper for build Fragment view
    protected abstract val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }

    protected fun observeViewModelFromActivity(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.activityActions.observe(this) { it((this.activity!!)) }
        }
    }

    protected fun observeViewModelForeverFromActivity(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.activityActions.observeForever { it((this.activity!!)) }
        }
    }

    protected fun observeViewModel(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.fragmentActions.observe(this) { it((this)) }
        }
    }

    protected fun observeViewModelForever(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.fragmentActions.observeForever { it((this)) }
        }
    }
}
