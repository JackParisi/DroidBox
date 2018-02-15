package com.github.giacomoparisi.droidboxsample.core.dagger.modules

import com.github.giacomoparisi.droidboxsample.ui.home.HomeActivity
import com.github.giacomoparisi.droidboxsample.ui.validator.ValidatorActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Giacomo Parisi on 14/12/17.
 * https://github.com/giacomoParisi
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeValidatorActivity(): ValidatorActivity
}
