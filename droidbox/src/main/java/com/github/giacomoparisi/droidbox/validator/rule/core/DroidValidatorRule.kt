package com.github.giacomoparisi.droidbox.validator.rule.core

import android.view.View

/**
 * Created by Giacomo Parisi on 29/01/18.
 * https://github.com/giacomoParisi
 */
abstract class DroidValidatorRule<ViewType : View, ValueType>(
        view: ViewType,
        protected var value: ValueType,
        protected var errorMessage: String) {

    var view: ViewType
        protected set

    init {
        this.view = view
    }

    fun validate(): Boolean {
        val valid = isValid(view)
        if (valid) {
            onValidationSucceeded(view)
        } else {
            onValidationFailed(view)
        }
        return valid
    }

    protected abstract fun isValid(view: ViewType): Boolean

    protected open fun onValidationSucceeded(view: ViewType) {}

    protected open fun onValidationFailed(view: ViewType) {}
}
