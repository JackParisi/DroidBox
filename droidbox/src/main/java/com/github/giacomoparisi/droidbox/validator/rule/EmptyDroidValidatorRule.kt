package com.github.giacomoparisi.droidbox.validator.rule

import android.widget.TextView
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule

/**
 * Created by Giacomo Parisi on 29/01/18.
 * https://github.com/giacomoParisi
 */
class EmptyDroidValidatorRule(
        view: TextView,
        value: Boolean?,
        errorMessage: String)
    : DroidValidatorRule<TextView, Boolean?>(view, value, errorMessage) {

    public override fun isValid(view: TextView): Boolean {
        return value != null &&
                value!! &&
                view.text.toString().isNotBlank() &&
                view.text.toString().isNotEmpty()
    }

    public override fun onValidationSucceeded(view: TextView) {
        DroidEditTextHelper.removeError(view)
    }

    public override fun onValidationFailed(view: TextView) {
        DroidEditTextHelper.setError(view, errorMessage)
    }
}
