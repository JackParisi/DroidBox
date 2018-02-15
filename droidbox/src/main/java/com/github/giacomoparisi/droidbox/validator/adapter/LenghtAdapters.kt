package com.github.giacomoparisi.droidbox.validator.adapter

import android.databinding.BindingAdapter
import android.widget.TextView
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.utility.DroidResourcesHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidViewTagHelper
import com.github.giacomoparisi.droidbox.validator.rule.EmptyDroidValidatorRule
import com.github.giacomoparisi.droidbox.validator.rule.MaxLenghtDroidValidatorRule
import com.github.giacomoparisi.droidbox.validator.rule.MinLenghtDroidValidatorRule

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

@BindingAdapter("validator_maxLength", "validator_maxLength_ErrorMessage", "validator_maxLength_AutoDismiss", requireAll = false)
fun bindValidatorMaxLength(view: TextView, maxLength: Int, errorMessage: String?, autoDismiss: Boolean?) {
    if (autoDismiss != null && autoDismiss) {
        DroidEditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = DroidResourcesHelper.getStringOrDefault(view,
            errorMessage, R.string.error_message_max_length, maxLength)
    DroidViewTagHelper.appendValue(R.id.validator_rule, view, MaxLenghtDroidValidatorRule(view, maxLength, handledErrorMessage))
}

@BindingAdapter("validator_minLength", "validator_minLength_ErrorMessage", "validator_minLength_AutoDismiss", requireAll = false)
fun bindValidatorMinLength(view: TextView, minLength: Int, errorMessage: String?, autoDismiss: Boolean?) {
    if (autoDismiss != null && autoDismiss) {
        DroidEditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = DroidResourcesHelper.getStringOrDefault(view,
            errorMessage, R.string.error_message_min_length, minLength)
    DroidViewTagHelper.appendValue(R.id.validator_rule, view, MinLenghtDroidValidatorRule(view, minLength, handledErrorMessage))
}