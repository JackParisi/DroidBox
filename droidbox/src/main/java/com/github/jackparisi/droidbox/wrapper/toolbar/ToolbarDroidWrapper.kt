package com.github.jackparisi.droidbox.wrapper.toolbar

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidbox.wrapper.DroidWrapper
import timber.log.Timber

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

/**
 *
 * DroidWrapper for toolbar.
 * Put the page layout below the toolbar using a linearLayout
 */
class ToolbarDroidWrapper : DroidWrapper(){

    override fun wrapLayout(viewModel: DroidViewModel, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams): View {

        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        if(wrapperLayout != null) {
            linearLayout.addView(wrapperLayout)
        }else{
            //TODO throw exception
            Timber.e("Toolbar wrapper is null")
        }
        linearLayout.addView(pageLayout, params)

        return linearLayout
    }
}