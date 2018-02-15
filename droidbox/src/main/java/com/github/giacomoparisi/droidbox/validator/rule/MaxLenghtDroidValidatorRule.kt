package com.github.giacomoparisi.droidbox.validator.rule

import android.widget.TextView
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
class MaxLenghtDroidValidatorRule(
        view: TextView,
        value: Int,
        errorMessage: String)
    : DroidValidatorRule<TextView, Int>(view, value, errorMessage) {

    public override fun isValid(view: TextView): Boolean {
        return view.length() <= value
    }

    public override fun onValidationSucceeded(view: TextView) {
        DroidEditTextHelper.removeError(view)
    }

    public override fun onValidationFailed(view: TextView) {
        DroidEditTextHelper.setError(view, errorMessage)
    }
}