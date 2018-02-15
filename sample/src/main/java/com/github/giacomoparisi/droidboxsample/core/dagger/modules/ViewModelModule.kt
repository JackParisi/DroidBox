package com.github.giacomoparisi.droidboxsample.core.dagger.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


import com.github.giacomoparisi.droidboxsample.core.dagger.DroidBoxSampleViewModelFactory
import com.github.giacomoparisi.droidboxsample.ui.home.HomeViewModel
import com.github.giacomoparisi.droidboxsample.ui.validator.ValidatorViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Giacomo Parisi on 14/12/17.
 * https://github.com/giacomoParisi
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(mainViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ValidatorViewModel::class)
    internal abstract fun bindValidatorViewModel(shareViewModel: ValidatorViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: DroidBoxSampleViewModelFactory): ViewModelProvider.Factory
}
