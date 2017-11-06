package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.request.RequestListener
import com.github.jackparisi.droidbox.architecture.glide.GlideApp
import com.github.jackparisi.droidbox.architecture.glide.GlideRequest

/**
 * Created by Giacomo Parisi on 22/07/17.
 * https://github.com/JackParisi
 */


/**
 *
 * Get image form NETWORK-BYTE-RESOURCES image and set it in the imageView with the required properties
 *
 * @param view The imageView that need the image
 * @param url The endpoint/url of the image for download
 * @param baseUrl The baseUrl to use with the url param imageUrl = baseUrl + url
 * @param centerCrop Set it to true if you want that Glide use centerCrop for the image
 * @param dynamicHeight Set it to true if you want the height of the view to be adjusted proportionally based on the height of the downloaded image
 */
@BindingAdapter(
        "image_url",
        "image_baseUrl",
        "image_res",
        "image_byte",
        "image_centerCrop",
        "image_dynamicHeight",
        "image_clearOnReload",
        "image_listener",
        requireAll = false)
fun bindImage(
        view: ImageView,
        url: String?,
        baseUrl: String?,
        resId: Int?,
        byte: String?,
        centerCrop: Boolean?,
        dynamicHeight: Boolean?,
        clearOnReload: Boolean?,
        imageListener: RequestListener<Bitmap>?) {

    if (clearOnReload != null && clearOnReload) {
        view.setImageResource(android.R.color.transparent)
    }

    if (!url.isNullOrBlank()) {
        // LOAD IMAGE FROM NETWORK (baseUrl + url or url)
        val urlRequest = GlideApp.with(view.context).asBitmap().load(if (baseUrl != null) baseUrl + url else url)
        executeGlideOperations(urlRequest, view, centerCrop, dynamicHeight, imageListener)

    } else if (!byte.isNullOrBlank()) {

        // LOAD IMAGE FROM BYTE ARRAY
        val imageByteArray = Base64.decode(byte, Base64.DEFAULT)
        val byteRequest = GlideApp.with(view.context).asBitmap().load(imageByteArray)
        executeGlideOperations(byteRequest, view, centerCrop, dynamicHeight, imageListener)

    } else if (resId != null) {

        // LOAD IMAGE FROM RESOURCE
        view.setImageResource(resId)
        if (centerCrop != null && centerCrop) {
            view.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }
}

/**
 *
 * Set the property for the glide request and set the result in the imageView
 *
 * @param glideRequest The glide request for the image
 * @param view The imageView that need the image
 * @param centerCrop Set it to true if you want that Glide use centerCrop for the image
 * @param dynamicHeight Set it to true if you want the height of the view to be adjusted proportionally based on the height of the downloaded image
 * @param imageListener Glide image listener for perform custom action on image load with success or fail
 */
private fun executeGlideOperations(
        glideRequest: GlideRequest<Bitmap>,
        view: ImageView, centerCrop: Boolean?,
        dynamicHeight: Boolean?,
        imageListener: RequestListener<Bitmap>?) {

    // SELECT SCALE TYPE METHOD
    if (centerCrop != null && centerCrop) {
        glideRequest.centerCrop()
    } else {
        glideRequest.fitCenter()
    }

    if (dynamicHeight != null && dynamicHeight) {
        view.layout(0, 0, 0, 0)
    }

    if (imageListener != null) {
        glideRequest.listener(imageListener)
    }

    glideRequest.into(view)
}
