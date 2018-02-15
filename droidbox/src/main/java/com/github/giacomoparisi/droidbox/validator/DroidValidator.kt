package com.github.giacomoparisi.droidbox.validator

import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.github.giacomoparisi.droidbox.R
import com.github.giacomoparisi.droidbox.validator.helper.DroidViewTagHelper
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule
import java.util.*
import javax.inject.Inject


/**
 * Created by Giacomo Parisi on 29/01/18.
 * https://github.com/giacomoParisi
 */
class DroidValidator @Inject constructor() {

    companion object {

        private const val FIELD_VALIDATION_MODE = 0
        private const val FORM_VALIDATION_MODE = 1
    }

    var target: ViewDataBinding? = null
    var validationListener: ValidationListener? = null

    private var mode = FIELD_VALIDATION_MODE
    private val disabledViews: MutableSet<View> = mutableSetOf()

    fun toValidate() {
        if (validationListener == null) throw IllegalArgumentException("Validation listener should not be null.")

        if (validate()) {
            validationListener!!.onValidationSuccess()
        } else {
            validationListener!!.onValidationError()
        }
    }

    fun validate(): Boolean {
        val viewWithValidations = getViewsWithValidation()
        return isAllViewsValid(viewWithValidations)
    }

    fun validate(view: View): Boolean {
        val viewWithValidations = getViewsWithValidation(view)
        return isAllViewsValid(viewWithValidations)
    }

    fun <ViewType : View> validate(views: List<ViewType>): Boolean {
        val viewWithValidations = getViewsWithValidation(views)
        return isAllViewsValid(viewWithValidations)
    }

    private fun isAllViewsValid(viewWithValidations: List<View>): Boolean {
        var allViewsValid = true
        for (viewWithValidation in viewWithValidations) {
            var viewValid = true
            val rules = viewWithValidation.getTag(R.id.validator_rule) as List<DroidValidatorRule<*, *>>
            for (rule in rules) {
                viewValid = viewValid && isRuleValid(rule)
                allViewsValid = allViewsValid && viewValid
            }

            if (mode == FIELD_VALIDATION_MODE && !viewValid) {
                break
            }
        }
        return allViewsValid
    }

    private fun isRuleValid(rule: DroidValidatorRule<*, *>): Boolean {
        return disabledViews.contains(rule.view) || rule.validate()
    }

    fun disableValidation(view: View) {
        disabledViews.add(view)
    }

    fun enableValidation(view: View) {
        disabledViews.remove(view)
    }

    fun enableFormValidationMode() {
        this.mode = FORM_VALIDATION_MODE
    }

    fun enableFieldValidationMode() {
        this.mode = FIELD_VALIDATION_MODE
    }

    private fun getViewsWithValidation(): List<View> {
        return if (target != null) {
            if (target!!.root is ViewGroup) {
                DroidViewTagHelper.getViewsByTag(target!!.root as ViewGroup, R.id.validator_rule)
            } else Collections.singletonList(target!!.root)
        } else {
            listOf()
        }
    }

    private fun <ViewType : View> getViewsWithValidation(views: List<ViewType>): List<View> {
        return DroidViewTagHelper.filterViewsWithTag(R.id.validator_rule, views)
    }

    private fun getViewsWithValidation(view: View): List<View> {
        return DroidViewTagHelper.filterViewWithTag(R.id.validator_rule, view)
    }

    interface ValidationListener {

        fun onValidationSuccess()

        fun onValidationError()
    }
}