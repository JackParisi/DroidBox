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
@BindingAdapter("srcUrlCenterCrop", "baseUrl", requireAll = false)
fun bindImageUrlCenterCrop(v: ImageView, url: String?, baseUrl: String?) {
    if (url != null && !url.isEmpty()) {
        Glide.with(v.context)
                .load(if (baseUrl != null) baseUrl + url else url)
                .centerCrop()
                .into(v)
    }
}


/**
 * BYTE
 */
@BindingAdapter("srcByteCenterCrop")
fun bindImageByteCenterCrop(v: ImageView, byte: String?) {

    if (byte != null && !byte.isEmpty()) {

        val imageByteArray = Base64.decode(byte, Base64.DEFAULT)

        Glide.with(v.context)
                .load(imageByteArray)
                .centerCrop()
                .into(v)
    }
}

@BindingAdapter("srcByte")
fun bindImageByte(v: ImageView, byte: String?) {

    if (byte != null && !byte.isEmpty()) {

        val imageByteArray = Base64.decode(byte, Base64.DEFAULT)

        Glide.with(v.context)
                .load(imageByteArray)
                .into(v)
    }
}