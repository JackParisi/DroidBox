package com.github.jackparisi.droidboxsample.core

import dagger.Component
import javax.inject.Singleton

/**
 * Created by Giacomo Parisi on 24/10/17.
 * https://github.com/JackParisi
 */

@Singleton
@Component(modules = arrayOf(DroidBoxSampleModule::class))
interface DroidBoxSampleComponent