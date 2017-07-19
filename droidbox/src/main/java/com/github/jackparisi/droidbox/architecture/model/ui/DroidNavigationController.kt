package com.github.jackparisi.droidbox.architecture.model.ui

import android.content.Intent
import android.support.v4.app.FragmentActivity

/**
 * Created by Giacomo Parisi on 18/07/17.
 * https://github.com/JackParisi
 */

class DroidNavigationController {

    fun openActivity(currentActivity: FragmentActivity, nextActivity: Class<*>, finish: Boolean = false){
        val intent = Intent(currentActivity, nextActivity)
        currentActivity.startActivity(intent)
        if(finish){
            currentActivity.finish()
        }
    }
}
