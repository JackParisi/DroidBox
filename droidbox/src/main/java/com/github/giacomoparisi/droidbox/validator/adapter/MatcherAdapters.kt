package com.github.giacomoparisi.droidbox.validator.adapter

import android.databinding.BindingAdapter
import android.support.annotation.IdRes
import android.widget.TextView
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.utility.DroidResourcesHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidViewTagHelper
import com.github.giacomoparisi.droidbox.validator.rule.MatcherDroidValidatorRule

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */

@BindingAdapter("validator_match", "validator_match_ErrorMessage", "validator_match_AutoDismiss", requireAll = false)
fun bindValidatorMatch(view: TextView, @IdRes match: Int, errorMessage: String?, autoDismiss: Boolean?) {
    if (autoDismiss != null && autoDismiss) {
        DroidEditTextHelper.disableErrorOnChanged(view)
    }

    val matchView = view.rootView.findViewById<TextView>(match)
    if (matchView != null) {
        val handledErrorMessage = DroidResourcesHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_match_validation)
        DroidViewTagHelper.appendValue(
                R.id.validator_rule,
                view,
                MatcherDroidValidatorRule(
                        view,
                        matchView,
                        handledErrorMessage,
                        true
                )
        )
    }
}