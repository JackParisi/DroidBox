package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

/**
 * Created by Giacomo Parisi on 22/07/17.
 * https://github.com/JackParisi
 */


/**
 * RESOURCES
 */
@BindingAdapter("imageRes_src")
fun bindSrcRes(v: ImageView, i: Int) {
    v.setImageResource(i)
}


/**
 * NETWORK
 */
@BindingAdapter("imageUrl_src", "imageUrl_baseUrl", "imageUrl_centerCrop", "imageUrl_dynamicHeight", requireAll = false)
fun bindImageUrl(v: ImageView, url: String?, baseUrl: String?, centerCrop: Boolean?, dynamicHeight: Boolean?) {
    if (url != null && !url.isEmpty()) {
        val request = Glide.with(v.context).load(if (baseUrl != null) baseUrl + url else url)

        if (centerCrop != null && centerCrop) {
            request.centerCrop()
        } else {
            request.fitCenter()
        }

        if (dynamicHeight != null && dynamicHeight) {
            request.asBitmap()
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                            if (resource != null) {
                                val height = (v.width / resource.width) * resource.height
                                val layoutParams = v.layoutParams
                                layoutParams.height = height
                                v.layoutParams = layoutParams
                                val bitmap = Bitmap.createScaledBitmap(resource, v.width, height, false)
                                v.setImageBitmap(bitmap)
                            }
                        }

                    })
        } else {
            request.into(v)
        }
    }
}

/**
 * BYTE
 */
@BindingAdapter("imageByte_src", "imageByte_centerCrop", requireAll = false)
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