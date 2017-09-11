package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.text.Html
import android.widget.TextView

/**
 * Created by Giacomo Parisi on 11/09/2017.
 * https://github.com/JackParisi
 */

@BindingAdapter("textHtml")
fun bindTextHtml(view: TextView, html: String?) {

    if (html != null) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            view.text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            view.text = Html.fromHtml(html)
        }
    }
}