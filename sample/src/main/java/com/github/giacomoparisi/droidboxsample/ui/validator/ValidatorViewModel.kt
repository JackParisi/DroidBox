package com.github.giacomoparisi.droidboxsample.ui.validator

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIActions
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIManager
import com.github.giacomoparisi.droidbox.validator.DroidValidator
import com.github.giacomoparisi.droidboxsample.core.Navigator
import com.github.giacomoparisi.droidboxsample.core.droidbox.DroidBoxSampleViewModel
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
class ValidatorViewModel @Inject constructor(
        application: Application,
        activityActions: DroidUIActions<androidx.fragment.app.FragmentActivity>,
        fragmentActions: DroidUIActions<androidx.fragment.app.Fragment>,
        droidUIManager: DroidUIManager,
        navigator: Navigator,
        val validator: DroidValidator) :
        DroidBoxSampleViewModel(
                application,
                activityActions,
                fragmentActions,
                droidUIManager,
                navigator) {

    init {
        validator.enableFormValidationMode()
    }

    val validationResult: ObservableField<String> = ObservableField()
    val emptyVisibility: ObservableBoolean = ObservableBoolean(true)

    fun validateForm() {
        val result = validator.validate()
        validationResult.set(if (result) "VALIDATION OK" else "VALIDATION ERROR")
    }

    fun updateEmptyVisibility() {
        emptyVisibility.set(!emptyVisibility.get())
    }
}