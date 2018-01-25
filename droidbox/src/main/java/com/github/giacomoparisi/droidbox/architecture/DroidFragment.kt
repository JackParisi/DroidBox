package com.github.giacomoparisi.droidbox.architecture

import android.support.v4.app.Fragment
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidView
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidFragment<out W : DroidWrapperService> : Fragment(), DroidView {

    // Wrapper for build Fragment view
    protected abstract val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }

    protected fun observeViewModelFromActivity(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.fragmentActivity.observe(this) { it((this.activity!!)) }
        }
    }

    protected fun observeViewModelForeverFromActivity(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.fragmentActivity.observeForever { it((this.activity!!)) }
        }
    }

    protected fun observeViewModel(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.fragment.observe(this) { it((this)) }
        }
    }

    protected fun observeViewModelForever(viewModel: DroidViewModel) {
        if (activity != null) {
            viewModel.fragment.observeForever { it((this)) }
        }
    }
}
