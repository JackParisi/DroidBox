package com.github.giacomoparisi.droidbox.validator.rule

import android.util.Patterns
import android.widget.TextView
import com.github.giacomoparisi.droidbox.validator.adapter.type.EValidatorType
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
class EmailDroidValidatorRule(
        view: TextView,
        value: EValidatorType,
        errorMessage: String)
    : DroidValidatorRule<TextView, EValidatorType>(view, value, errorMessage) {

    public override fun isValid(view: TextView): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS
        return emailPattern.matcher(view.text).matches()
    }

    public override fun onValidationSucceeded(view: TextView) {
        DroidEditTextHelper.removeError(view)
    }

    public override fun onValidationFailed(view: TextView) {
        DroidEditTextHelper.setError(view, errorMessage)
    }
}