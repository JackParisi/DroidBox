package com.github.giacomoparisi.droidbox.validator.adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.View
import com.github.giacomoparisi.droidbox.validator.DroidValidator

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */

@BindingAdapter("validator")
fun bindValidator(view: View, validator: DroidValidator?) {
    if (validator != null) {
        val dataBindingView = DataBindingUtil.findBinding<ViewDataBinding>(view)
        if (dataBindingView != null) {
            validator.target = dataBindingView
        }
    }
}