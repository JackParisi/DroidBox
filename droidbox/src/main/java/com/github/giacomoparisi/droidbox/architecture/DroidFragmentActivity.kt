package com.github.giacomoparisi.droidbox.architecture

import android.support.v4.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.DroidViewModel
import com.github.giacomoparisi.droidbox.wrapper.DroidWrapperService

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

abstract class DroidFragmentActivity<out W : DroidWrapperService> : FragmentActivity() {

    // Wrapper for build Activity view
    abstract protected val wrapper: W

    override fun onDestroy() {
        super.onDestroy()
        wrapper.onViewDestroy()
    }

    protected fun observeViewModel(viewModel: DroidViewModel) {
        viewModel.droidUiActions.observe(this) { it(this) }
    }

    protected fun observeViewModelForever(viewModel: DroidViewModel) {
        viewModel.droidUiActions.observeForever { it(this) }
    }
}