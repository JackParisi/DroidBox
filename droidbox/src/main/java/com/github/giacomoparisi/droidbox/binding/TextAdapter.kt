package com.github.giacomoparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Paint
import android.text.Html
import android.widget.TextView

/**
 * Created by Giacomo Parisi on 11/09/2017.
 * https://github.com/giacomoParisi
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
fun bindTextRes(view: TextView, res: Int?) = if (res != null && res != 0) {
    view.setText(res)
} else {
    view.setText("")
}

/**
 *
 * Underline the text
 *
 * @param view The text view that has the text to underline
 * @param enable Set to true to enable underline
 */
@BindingAdapter("text_Underline")
fun bindTextUnderline(view: TextView, enable: Boolean) {
    if (enable) {
        view.paintFlags = view.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    } else {
        view.paintFlags = view.paintFlags xor Paint.UNDERLINE_TEXT_FLAG
    }
}
