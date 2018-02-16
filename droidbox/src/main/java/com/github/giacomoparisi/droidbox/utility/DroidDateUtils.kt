package com.github.giacomoparisi.droidbox.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Giacomo Parisi on 08/02/18.
 * https://github.com/giacomoParisi
 */

fun formatDateString(date: String? = null,
                     placeholder: String? = null,
                     errorPlaceholder: String? = null,
                     originalFormat: String? = null,
                     targetFormat: String? = null,
                     originalLocale: Locale? = null,
                     targetLocale: Locale? = null): String? {
    if (!originalFormat.isNullOrEmpty() && !targetFormat.isNullOrEmpty() && !date.isNullOrEmpty()) {
        try {
            val originalDateFormat = SimpleDateFormat(
                    originalFormat,
                    originalLocale ?: Locale.getDefault())
            val targetDateFormat = SimpleDateFormat(
                    targetFormat,
                    targetLocale ?: Locale.getDefault())
            val originalDate = originalDateFormat.parse(date)
            return targetDateFormat.format(originalDate)
        } catch (error: ParseException) {
            return if (!placeholder.isNullOrEmpty()) {
                errorPlaceholder
            } else if (!date.isNullOrEmpty()) {
                date
            } else {
                ""
            }
        }
    } else if (!placeholder.isNullOrEmpty()) {
        return placeholder
    } else if (!date.isNullOrEmpty()) {
        return date
    } else {
        return ""
    }
}

fun toDate(date: String, format: String, locale: Locale? = null): Date? {
    val dateFormat = SimpleDateFormat(format, locale ?: Locale.getDefault())

    return try {
        dateFormat.parse(date)
    } catch (e: ParseException) {
        null
    }
}



