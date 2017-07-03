package com.github.jackparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView


/**
 * Created by Giacomo Parisi on 02/07/2017.
 * https://github.com/JackParisi
 */

object BindingAdapters {

    @BindingAdapter("visibleOrGone")
    fun bindVisibleOrGone(v: View, visible: Boolean) {
        v.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("visibleOrInvisible")
    fun bindVisibleOrInvisible(v: View, visible: Boolean) {
        v.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter("loading")
    fun bindLoading(v: ImageView, loading: Boolean) {
        if (loading) {
            val rotate = RotateAnimation(
                    0f,
                    360f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f)
            rotate.duration = 800
            rotate.interpolator = LinearInterpolator()
            rotate.repeatCount = Animation.INFINITE
            v.startAnimation(rotate)
        } else {
            v.animation = null
        }
    }

    @BindingAdapter("bounce")
    fun bindBounce(v: View, enabled: Boolean) {
        if (enabled) {
            v.setOnTouchListener(object : View.OnTouchListener {
                internal var touchOutside = true

                override fun onTouch(v1: View, event: MotionEvent): Boolean {
                    var response = false
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            scaleView(v, .9f)
                            touchOutside = false
                            response = true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val rect = Rect(v.left, v.top, v.right, v.bottom)
                            if (!rect.contains(v.left + event.x.toInt(), v.top + event.y.toInt()) && !touchOutside) {
                                // User moved outside bounds
                                touchOutside = true
                                scaleView(v, 1f)
                            } else if (rect.contains(v.left + event.x.toInt(), v.top + event.y.toInt()) && touchOutside) {
                                scaleView(v, .9f)
                                touchOutside = false
                            }
                        }
                        MotionEvent.ACTION_CANCEL -> {
                            scaleView(v, 1f)
                            touchOutside = true
                        }
                        MotionEvent.ACTION_UP -> {
                            scaleView(v, 1f)
                            if (!touchOutside) {
                                v.callOnClick()
                            }
                            touchOutside = true
                        }
                    }
                    return response
                }
            })
        } else {
            v.setOnTouchListener(null)
        }
    }

    fun scaleView(v: View, endScale: Float) {
        v.animate().scaleX(endScale)
                .scaleY(endScale).duration = 100
    }
}
