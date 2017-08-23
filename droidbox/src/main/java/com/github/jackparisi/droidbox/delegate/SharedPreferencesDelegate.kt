package com.github.jackparisi.droidbox.delegate

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Giacomo Parisi on 22/08/17.
 * https://github.com/JackParisi
 */

private inline fun <T> SharedPreferences.delegate(
        defaultValue: T,
        key: String?,
        crossinline getter:
        SharedPreferences.(String, T) -> T,
        crossinline setter:
        SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {

    return object : ReadWriteProperty<Any, T> {

        override fun getValue(thisRef: Any, property: KProperty<*>): T = getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = edit().setter(key ?: property.name, value).apply()

    }
}

fun SharedPreferences.int(defaultValue: Int = -1, key: String? = null) = delegate(
        defaultValue = defaultValue,
        key = key,
        getter = SharedPreferences::getInt,
        setter = SharedPreferences.Editor::putInt)

fun SharedPreferences.long(defaultValue: Long = -1, key: String? = null) = delegate(
        defaultValue = defaultValue,
        key = key,
        getter = SharedPreferences::getLong,
        setter = SharedPreferences.Editor::putLong)

fun SharedPreferences.float(defaultValue: Float = -1f, key: String? = null) = delegate(
        defaultValue = defaultValue,
        key = key,
        getter = SharedPreferences::getFloat,
        setter = SharedPreferences.Editor::putFloat)

fun SharedPreferences.boolean(defaultValue: Boolean = false, key: String? = null) = delegate(
        defaultValue = defaultValue,
        key = key,
        getter = SharedPreferences::getBoolean,
        setter = SharedPreferences.Editor::putBoolean)

fun SharedPreferences.string(defaultValue: String = "", key: String? = null) = delegate(
        defaultValue = defaultValue,
        key = key,
        getter = SharedPreferences::getString,
        setter = SharedPreferences.Editor::putString)