package com.github.giacomoparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout

/**
 * Created by Giacomo Parisi on 20/12/17.
 * https://github.com/giacomoParisi
 */

@BindingAdapter("textInputError_input", "textInputError_validator")
fun bindTextError(view: TextInputLayout, input: String?, validator: (String?) -> String?) {

    if (input != null) {
        view.error = validator(input)
    } else {
        view.error = null
    }
}
