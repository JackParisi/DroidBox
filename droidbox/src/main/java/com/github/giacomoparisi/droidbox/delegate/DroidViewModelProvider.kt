@file:Suppress("UNCHECKED_CAST")

package com.github.giacomoparisi.droidbox.delegate

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by Giacomo Parisi on 12/07/2017.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Delegate that initialize the android viewModel for Fragments by the provider function
 *
 * @param mode The LazyThreadSafetyMode used for the initialization
 * (default: LazyThreadSafetyMode.NONE)
 * @param provider The function that provides the viewModel
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
        mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
        crossinline provider: () -> VM) = lazy(mode) {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T1 : ViewModel> create(aClass: Class<T1>) = provider() as T1
    }).get(VM::class.java)
}

/**
 *
 * Delegate that initialize the android viewModel for FragmentActivities by the provider function
 *
 * @param mode The LazyThreadSafetyMode used for the initialization
 * (default: LazyThreadSafetyMode.NONE)
 * @param provider The function that provides the viewModel
 */
inline fun <reified VM : ViewModel> AppCompatActivity.viewModelProvider(
        mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
        crossinline provider: () -> VM) = lazy(mode) {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T1 : ViewModel> create(aClass: Class<T1>) = provider() as T1
    }).get(VM::class.java)
}