package com.github.giacomoparisi.droidbox.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Giacomo Parisi on 20/12/17.
 * https://github.com/giacomoParisi
 */

@BindingAdapter("textInputError_input", "textInputError_validator")
fun bindTextError(view: com.google.android.material.textfield.TextInputLayout, input: String?, validator: (String?) -> String?) {

    if (input != null) {
        view.error = validator(input)
    } else {
        view.error = null
    }
}
