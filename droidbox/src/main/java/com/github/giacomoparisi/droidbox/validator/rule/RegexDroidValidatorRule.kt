package com.github.giacomoparisi.droidbox.validator.rule

import android.widget.TextView
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
class RegexDroidValidatorRule(
        view: TextView,
        value: String?,
        errorMessage: String)
    : DroidValidatorRule<TextView, String?>(view, value, errorMessage) {

    public override fun isValid(view: TextView): Boolean {
        if (view.text.toString().isEmpty() || view.text.toString().isBlank()) {
            return true
        }

        if (value == null || value!!.isEmpty() || value!!.isBlank()) {
            return false
        }

        return view.text.toString().matches(Regex(value!!))
    }

    public override fun onValidationSucceeded(view: TextView) {
        DroidEditTextHelper.removeError(view)
    }

    public override fun onValidationFailed(view: TextView) {
        DroidEditTextHelper.setError(view, errorMessage)
    }
}