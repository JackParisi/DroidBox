package com.github.giacomoparisi.droidboxsample.core

import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.github.giacomoparisi.droidbox.architecture.model.ui.DroidUIActions
import com.github.giacomoparisi.droidboxsample.ui.validator.ValidatorActivity
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 15/02/18.
 * https://github.com/giacomoParisi
 */
class Navigator @Inject constructor() {

    fun openValidator(droidUIActions: DroidUIActions<FragmentActivity>) {
        droidUIActions { fragmentActivity ->
            val validator = Intent(fragmentActivity, ValidatorActivity::class.java)
            fragmentActivity.startActivity(validator)
        }
    }
}