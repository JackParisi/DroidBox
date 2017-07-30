package com.github.jackparisi.droidbox.wrapper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import timber.log.Timber

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

class ToolbarDroidWrapper : DroidWrapper(){

    override fun wrapLayout(viewModel: DroidViewModel, pageLayout: View, wrapperLayout: View?, context: Context, params: ViewGroup.LayoutParams): View {

        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        if(wrapperLayout != null) {
            linearLayout.addView(wrapperLayout, params)
        }else{
            //TODO throw exception
            Timber.e("Toolbar wrapper is null")
        }
        linearLayout.addView(pageLayout, params)

        return linearLayout
    }
}
