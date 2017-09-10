package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Giacomo Parisi on 22/07/17.
 * https://github.com/JackParisi
 */


/**
 * RESOURCES
 */
@BindingAdapter("srcRes")
fun bindSrcRes(v: ImageView, i: Int) {
    v.setImageResource(i)
}


/**
 * NETWORK
 */
@BindingAdapter("srcUrl", "baseUrl", "centerCrop", requireAll = false)
fun bindImageUrl(v: ImageView, url: String?, baseUrl: String?, centerCrop: Boolean?) {
    if (url != null && !url.isEmpty()) {
        val request = Glide.with(v.context).load(if (baseUrl != null) baseUrl + url else url)

        if (centerCrop != null && centerCrop) {
            request.centerCrop()
        }

        request.into(v)
    }
}


/**
 * BYTE
 */
@BindingAdapter("srcByte", "centerCrop", requireAll = false)
fun bindImageByte(v: ImageView, byte: String?, centerCrop: Boolean?) {

    if (byte != null && !byte.isEmpty()) {

        val imageByteArray = Base64.decode(byte, Base64.DEFAULT)

        val request = Glide.with(v.context).load(imageByteArray)

        if (centerCrop != null && centerCrop) {
            request.centerCrop()
        }

        request.into(v)
    }
}