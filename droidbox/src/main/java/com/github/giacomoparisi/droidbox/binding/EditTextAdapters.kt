package com.github.giacomoparisi.droidbox.binding

import androidx.databinding.BindingAdapter
import android.widget.EditText
import com.github.giacomoparisi.droidbox.utility.formatDateString
import java.util.*

/**
 * Created by Giacomo Parisi on 08/02/18.
 * https://github.com/giacomoParisi
 */

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
        "text_date_error_placeholder",
        "text_date_original_format",
        "text_date_target_format",
        "text_date_original_locale",
        "text_date_target_locale",
        requireAll = false)
fun bindTextDate(
        view: EditText,
        date: String?,
        placeholder: String?,
        errorPlaceholder: String?,
        originalFormat: String?,
        targetFormat: String?,
        originalLocale: Locale?,
        targetLocale: Locale?) {
    view.setText(formatDateString(date, placeholder, errorPlaceholder, originalFormat, targetFormat, originalLocale, targetLocale))
}