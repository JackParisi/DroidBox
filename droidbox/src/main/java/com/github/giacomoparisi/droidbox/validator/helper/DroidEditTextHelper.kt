package com.github.giacomoparisi.droidbox.validator.helper

import androidx.databinding.adapters.ListenerUtil
import com.google.android.material.textfield.TextInputLayout
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.github.giacomoparisi.droidbox.R

/**
 * Created by Giacomo Parisi on 29/01/18.
 * https://github.com/giacomoParisi
 */
object DroidEditTextHelper {

    fun removeError(textView: TextView) {
        DroidEditTextHelper.setError(textView, null)
    }

    fun setError(textView: TextView, errorMessage: String?) {
        val textInputLayout = getTextInputLayout(textView)
        if (textInputLayout != null) {
            textInputLayout.isErrorEnabled = !TextUtils.isEmpty(errorMessage)
            textInputLayout.error = errorMessage
        } else {
            textView.error = errorMessage
        }
    }

    private fun getTextInputLayout(textView: TextView): com.google.android.material.textfield.TextInputLayout? {
        var textInputLayout: com.google.android.material.textfield.TextInputLayout? = null

        var parent = textView.parent
        while (parent is View) {
            if (parent is com.google.android.material.textfield.TextInputLayout) {
                textInputLayout = parent
                break
            }
            parent = parent.getParent()
        }
        return textInputLayout
    }

    fun disableErrorOnChanged(textView: TextView) {
        if (ListenerUtil.getListener<TextWatcher>(textView, R.id.text_watcher_clear_error) != null) {
            return
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                DroidEditTextHelper.setError(textView, null)
            }

            override fun afterTextChanged(s: Editable) {}
        }
        textView.addTextChangedListener(textWatcher)
        ListenerUtil.trackListener(textView, textView, R.id.text_watcher_clear_error)
    }

}
