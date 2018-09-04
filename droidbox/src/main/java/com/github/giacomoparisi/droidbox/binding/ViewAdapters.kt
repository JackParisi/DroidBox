package com.github.giacomoparisi.droidbox.binding

import androidx.databinding.BindingAdapter
import android.view.View


/**
 * Created by Giacomo Parisi on 02/07/2017.
 * https://github.com/giacomoParisi
 */

/**
 * Update the visibility of te view status VISIBLE/GONE based on boolean value
 *
 * @param view The view that need the visibility update
 * @param visible Set it to true for VISIBLE status or false for GONE status
 */
@BindingAdapter("view_visibleOrGone")
fun bindVisibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}


/**
 * Update the visibility of te view status VISIBLE/GONE based on object null value
 *
 * @param view The view that need the visibility update
 * @param visible If visible object is not null, set the visibility to VISIBLE else to GONE
 */
@BindingAdapter("view_visibleOrGone")
fun bindVisibleOrGone(view: View, visible: Any?) {
    view.visibility = if (visible != null) View.VISIBLE else View.GONE
}

/**
 * Update the visibility of te view status VISIBLE/GONE based on string value (null or empty)
 *
 * @param view The view that need the visibility update
 * @param visible If visible object is not null, set the visibility to VISIBLE else to GONE
 */
@BindingAdapter("view_visibleOrGone")
fun bindVisibleOrGone(view: View, visible: String?) {
    view.visibility = if (visible.isNullOrEmpty()) View.GONE else View.VISIBLE
}

/**
 *
 * Update the visibility of te view status VISIBLE/INVISIBLE based on boolean value
 *
 * @param view The view that need the visibility update
 * @param visible Set it to true for VISIBLE status or false for INVISIBLE status
 */
@BindingAdapter("view_visibleOrInvisible")
fun bindVisibleOrInvisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

/**
 * Update the visibility of te view status VISIBLE/GONE based on object null value
 *
 * @param view The view that need the visibility update
 * @param visible If visible object is not null, set the visibility to VISIBLE else to GONE
 */
@BindingAdapter("view_visibleOrInvisible")
fun bindVisibleOrInvisible(view: View, visible: Any?) {
    view.visibility = if (visible != null) View.VISIBLE else View.INVISIBLE
}

/**
 * Update the visibility of te view status VISIBLE/GONE based on string value (null or empty)
 *
 * @param view The view that need the visibility update
 * @param visible If visible object is not null, set the visibility to VISIBLE else to GONE
 */
@BindingAdapter("view_visibleOrInvisible")
fun bindVisibleOrInvisible(view: View, visible: String?) {
    view.visibility = if (visible.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
}

