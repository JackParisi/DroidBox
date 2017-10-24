package com.github.giacomoparisi.droidbox.architecture.model.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity

/**
 * Created by Giacomo Parisi on 18/07/17.
 * https://github.com/giacomoParisi
 */

class DroidNavigationController {

    /**
     *
     * Open new activity on top and optionally close the current activity
     * and/or send a bundle to the next activity
     *
     * @param currentActivity The current activity that want to open the new page
     * @param nextActivity The class type of the activity that will be opened
     * @param bundle The bundle that will wi pass to the next activity
     * @param finish Set it to true if you want to remove the current activity from the stack
     */
    fun openActivity(currentActivity: FragmentActivity, nextActivity: Class<*>, bundle: Bundle? = null, finish: Boolean = false){
        val intent = Intent(currentActivity, nextActivity)
        bundle?.let { intent.putExtras(bundle) }
        currentActivity.startActivity(intent)
        if(finish){
            currentActivity.finish()
        }
    }
}
