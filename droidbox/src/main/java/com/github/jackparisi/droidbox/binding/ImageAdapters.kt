package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
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
        requireAll = false)
fun bindImage(
        view: ImageView,
        url: String?,
        baseUrl: String?,
        resId: Int?,
        byte: String?,
        centerCrop: Boolean?,
        dynamicHeight: Boolean?) {

    view.setImageResource(0)

    if (!url.isNullOrBlank()) {
        // LOAD IMAGE FROM NETWORK (baseUrl + url or url)
        val urlRequest = GlideApp.with(view.context).asBitmap().load(if (baseUrl != null) baseUrl + url else url)
        executeGlideOperations(urlRequest, view, centerCrop, dynamicHeight)

    } else if (!byte.isNullOrBlank()) {

        // LOAD IMAGE FROM BYTE ARRAY
        val imageByteArray = Base64.decode(byte, Base64.DEFAULT)
        val byteRequest = GlideApp.with(view.context).asBitmap().load(imageByteArray)
        executeGlideOperations(byteRequest, view, centerCrop, dynamicHeight)

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
 */
private fun executeGlideOperations(glideRequest: GlideRequest<Bitmap>, view: ImageView, centerCrop: Boolean?, dynamicHeight: Boolean?) {

    // SELECT SCALE TYPE METHOD
    if (centerCrop != null && centerCrop) {
        glideRequest.centerCrop()
    } else {
        glideRequest.fitCenter()
    }

    if (dynamicHeight != null && dynamicHeight) {

        // UPDATE THE VIEW HEIGHT PROPORTIONALLY TO IMAGE HEIGHT
        glideRequest
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                        if (resource != null) {
                            val height = (view.width / resource.width) * resource.height
                            val layoutParams = view.layoutParams
                            layoutParams.height = height
                            view.layoutParams = layoutParams
                            val bitmap = Bitmap.createScaledBitmap(resource, view.width, height, false)
                            view.setImageBitmap(bitmap)
                        }
                    }
                })
    } else {
        glideRequest.into(view)
    }
}
