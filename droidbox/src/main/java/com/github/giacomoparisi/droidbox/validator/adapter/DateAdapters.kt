package com.github.giacomoparisi.droidbox.validator.adapter

import androidx.databinding.BindingAdapter
import androidx.annotation.IdRes
import android.widget.TextView
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.utility.DroidResourcesHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.helper.DroidViewTagHelper
import com.github.giacomoparisi.droidbox.validator.rule.DateDroidValidatorRule
import java.util.*

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */

@BindingAdapter(
        "validator_date_comparison",
        "validator_date_comparisonType",
        "validator_date_comparisonError",
        "validator_date_comparisonSourceFieldFormat",
        "validator_date_comparisonTargetFieldFormat",
        "validator_date_comparisonSourceFieldLocale",
        "validator_date_comparisonTargetFieldLocale",
        "validator_date_comparisonTimeZone",
        "validator_date_comparisonAutoDismiss",
        requireAll = false)
fun bindValidatorDate(view: TextView,
                      @IdRes comparisonViewId: Int?,
                      dateRuleType: DateDroidValidatorRule.EDateRuleType?,
                      errorMessage: String?,
                      viewDateFormat: String?,
                      valueDateFormat: String?,
                      viewLocale: Locale?,
                      valueLocale: Locale?,
                      timeZone: TimeZone?,
                      autoDismiss: Boolean?) {

    if (autoDismiss != null && autoDismiss) {
        DroidEditTextHelper.disableErrorOnChanged(view)
    }

    if (comparisonViewId != null) {
        val comparisonView = view.rootView.findViewById<TextView>(comparisonViewId);
        if (dateRuleType != null && comparisonView != null) {
            val handledErrorMessage = DroidResourcesHelper.getStringOrDefault(
                    view,
                    errorMessage,
                    dateRuleType.errorMessage
            )
            DroidViewTagHelper.appendValue(
                    R.id.validator_rule,
                    view,
                    DateDroidValidatorRule(
                            view,
                            comparisonView,
                            handledErrorMessage,
                            viewDateFormat,
                            valueDateFormat,
                            dateRuleType,
                            viewLocale,
                            valueLocale,
                            timeZone
                    )
            )
        }
    }
}
