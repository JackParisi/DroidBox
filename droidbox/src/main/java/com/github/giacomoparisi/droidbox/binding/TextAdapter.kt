package com.github.giacomoparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Paint
import android.text.Html
import android.widget.TextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
@BindingAdapter("text_html")
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
@BindingAdapter("text_res")
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
@BindingAdapter("text_underline")
fun bindTextUnderline(view: TextView, enable: Boolean) {
    if (enable) {
        view.paintFlags = view.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    } else {
        view.paintFlags = view.paintFlags xor Paint.UNDERLINE_TEXT_FLAG
    }
}

/**
 *
 * Change the date format of a date string, from original to target, and set it as text in the view
 *
 * @param view The text view that need the formatted date
 * @param date The date that needs to be formatted in the target format
 * @param originalFormat The original date format
 * @param targetFormat The target format of the date desired for viewing
 * @param originalLocale The locale object of the original date, used for parsing the original date (default is current phone locale)
 * @param targetLocale The locale object for the target date, used for the conversion (default is current phone locale)
 */
@BindingAdapter(
        "text_date",
        "text_date_placeholder",
        "text_date_original_format",
        "text_date_target_format",
        "text_date_original_locale",
        "text_date_target_locale",
        requireAll = false)
fun bindTextDate(
        view: TextView,
        date: String?,
        datePlaceholder: String?,
        originalFormat: String?,
        targetFormat: String?,
        originalLocale: Locale?,
        targetLocale: Locale?) {
    if (!originalFormat.isNullOrEmpty() && !targetFormat.isNullOrEmpty() && !date.isNullOrEmpty()) {
        try {
            val originalDateFormat = SimpleDateFormat(
                    originalFormat,
                    originalLocale ?: Locale.getDefault())
            val targetDateFormat = SimpleDateFormat(
                    targetFormat,
                    targetLocale ?: Locale.getDefault())
            val originalDate = originalDateFormat.parse(date)
            val targetDate = targetDateFormat.format(originalDate)
            view.text = targetDate
        } catch (error: ParseException) {
            if (!datePlaceholder.isNullOrEmpty()) {
                view.text = datePlaceholder
            } else if (!date.isNullOrEmpty()) {
                view.text = date
            } else {
                view.text = ""
            }
        }
    } else if (!datePlaceholder.isNullOrEmpty()) {
        view.text = datePlaceholder
    } else if (!date.isNullOrEmpty()) {
        view.text = date
    } else {
        view.text = ""
    }
}
