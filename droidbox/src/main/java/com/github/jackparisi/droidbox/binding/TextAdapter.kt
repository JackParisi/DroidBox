package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.text.Html
import android.widget.TextView

/**
 * Created by Giacomo Parisi on 11/09/2017.
 * https://github.com/JackParisi
 */


/**
 *
 * Format and set an HTML text in the textView
 *
 * @param view The textView that need the html text
 * @param html The html string
 */
@BindingAdapter("text_Html")
fun bindTextHtml(view: TextView, html: String?) {

    if (html != null) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            view.text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            view.text = Html.fromHtml(html)
        }
    }
}

/**
 *
 * Set text from resource id
 *
 * @param view The textView that need the text
 * @param res id of the resource
 */
@BindingAdapter("text_Res")
fun bindTextRes(view: TextView, res: Int?) =
        if (res != null) {
            view.setText(res)
        } else {
            view.setText("")
        }