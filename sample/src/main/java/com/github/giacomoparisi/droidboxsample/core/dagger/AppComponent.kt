package com.github.giacomoparisi.droidboxsample.core.dagger

import android.app.Application
import com.github.giacomoparisi.droidboxsample.core.DroidBoxSampleApp
import com.github.giacomoparisi.droidboxsample.core.dagger.modules.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Giacomo Parisi on 14/12/17.
 * https://github.com/giacomoParisi
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(droidBoxSampleApp: DroidBoxSampleApp)
}
