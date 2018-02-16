package com.github.giacomoparisi.droidbox.validator.rule

import android.widget.TextView
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.utility.toDate
import com.github.giacomoparisi.droidbox.validator.helper.DroidEditTextHelper
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule
import java.util.*

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
class DateDroidValidatorRule(
        view: TextView,
        value: TextView,
        errorMessage: String,
        private val viewDateFormat: String?,
        private val valueDateFormat: String?,
        private val dateRuleType: EDateRuleType?,
        private val viewLocale: Locale?,
        private val valueLocale: Locale?,
        private val timeZone: TimeZone?)
    : DroidValidatorRule<TextView, TextView>(view, value, errorMessage) {


    public override fun isValid(view: TextView): Boolean {

        if (viewDateFormat != null && valueDateFormat != null) {
            val viewDate = toDate(view.text.toString(), viewDateFormat, viewLocale)
            val valueDate = toDate(value.text.toString(), valueDateFormat, valueLocale)

            if (valueDate != null && viewDate != null && dateRuleType != null) {
                return when (dateRuleType) {
                    EDateRuleType.AFTER -> viewDate > valueDate
                    EDateRuleType.BEFORE -> viewDate < valueDate
                    EDateRuleType.EQUAL -> viewDate.compareTo(valueDate) == 0
                }
            }
        }
        return false
    }

    public override fun onValidationSucceeded(view: TextView) {
        DroidEditTextHelper.removeError(view)
    }

    public override fun onValidationFailed(view: TextView) {
        DroidEditTextHelper.setError(view, errorMessage)
    }

    enum class EDateRuleType(val errorMessage: Int) {
        AFTER(R.string.error_message_date_after_validation),
        BEFORE(R.string.error_message_date_before_validation),
        EQUAL(R.string.error_message_date_equal_validation)
    }
}