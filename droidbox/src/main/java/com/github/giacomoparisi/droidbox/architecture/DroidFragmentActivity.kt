package com.github.giacomoparisi.droidbox.architecture

import androidx.fragment.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidView
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidFragmentActivity<out W : DroidWrapperService> : androidx.fragment.app.FragmentActivity(), DroidView {

    // Wrapper for build Activity view
    protected abstract val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }

    protected fun observeViewModel(viewModel: DroidViewModel) {
        viewModel.activityActions.observe(this) { it(this) }
    }

    protected fun observeViewModelForever(viewModel: DroidViewModel) {
        viewModel.activityActions.observeForever { it(this) }
    }
}