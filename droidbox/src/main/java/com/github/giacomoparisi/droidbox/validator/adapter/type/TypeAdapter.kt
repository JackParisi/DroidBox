package com.github.giacomoparisi.droidbox.validator.adapter.type

import android.databinding.BindingAdapter
import android.widget.TextView
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.utility.DroidResourcesHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidViewTagHelper
import com.github.giacomoparisi.droidbox.validator.rule.EmailDroidValidatorRule
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */

@BindingAdapter("validator_type", "validator_type_ErrorMessage", "validator_type_AutoDismiss", requireAll = false)
fun bindingTypeValidation(view: TextView, validationType: EValidatorType, errorMessage: String?, autoDismiss: Boolean?) {
    if (autoDismiss != null && autoDismiss) {
        DroidEditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = DroidResourcesHelper.getStringOrDefault(view,
            errorMessage, validationType.errorMessage)
    DroidViewTagHelper.appendValue(R.id.validator_rule, view, getTypeRule(view, validationType, handledErrorMessage))
}

private fun getTypeRule(view: TextView, validationType: EValidatorType, errorMessage: String): DroidValidatorRule<*, *> {
    when (validationType) {
        EValidatorType.EMAIL -> return EmailDroidValidatorRule(view, validationType, errorMessage)
    }
}