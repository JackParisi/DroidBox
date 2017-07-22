package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Rect
import android.util.Base64
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * Created by Giacomo Parisi on 02/07/2017.
 * https://github.com/JackParisi
 */


@BindingAdapter("visibleOrGone")
fun bindVisibleOrGone(v: View, visible: Boolean) {
    v.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun bindVisibleOrInvisible(v: View, visible: Boolean) {
    v.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}
