package com.github.giacomoparisi.droidbox.validator.adapter

import android.databinding.BindingAdapter
import android.widget.TextView
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.utility.DroidResourcesHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidViewTagHelper
import com.github.giacomoparisi.droidbox.validator.rule.EmptyDroidValidatorRule

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */

@BindingAdapter("validator_empty", "validator_empty_ErrorMessage", "validator_empty_AutoDismiss", requireAll = false)
fun bindValidatorEmpty(view: TextView, empty: Boolean, errorMessage: String?, autoDismiss: Boolean?) {
    if (autoDismiss != null && autoDismiss) {
        DroidEditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = DroidResourcesHelper.getStringOrDefault(view,
            errorMessage, R.string.error_message_empty_validation)
    DroidViewTagHelper.appendValue(R.id.validator_rule, view, EmptyDroidValidatorRule(view, empty, handledErrorMessage))
}