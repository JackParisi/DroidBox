package com.github.giacomoparisi.droidbox.binding

import android.databinding.BindingAdapter
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

/**
 * Created by Giacomo Parisi on 22/07/17.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Add a rotation animation to the view
 *
 * @param view The view that need the animation
 * @param enabled Set it to true to start the animation
 * @param start The starting angle of animation
 * @param end The final angle of the animation
 * @param duration The duration (milliseconds) of the animation
 * @param pivotX The X coordinate of the point about which the object is being rotated,
 * specified as an absolute number where 0 is the left edge.
 * @param pivotY The Y coordinate of the point about which the object is being rotated,
 * specified as an absolute number where 0 is the top edge.
 * @param repeatCount Defines how many times the animation should repeat. The default value is 0.
 * @param reverse Set to true if you want to reverse the animation when it
 * reaches the end (default LOOP)
 * @param startOffset Delay in milliseconds before the animation runs, once start time is reached.
 */
@BindingAdapter(
        "animationRotation_enable",
        "animationRotation_start",
        "animationRotation_end",
        "animationRotation_duration",
        "animationRotation_pivotX",
        "animationRotation_pivotY",
        "animationRotation_repeatCount",
        "animationRotation_reverse",
        "animationRotation_startOffset",
        requireAll = false)
fun bindLoading(
        view: View,
        enabled: Boolean?,
        start: Float?,
        end: Float?,
        duration: Long?,
        pivotX: Float?,
        pivotY: Float?,
        repeatCount: Int?,
        reverse: Boolean?,
        startOffset: Long?) {
    if (enabled != null && enabled) {
        val rotate = RotateAnimation(
                start ?: 0f,
                end ?: 360f,
                Animation.RELATIVE_TO_SELF,
                pivotX ?: 0.5f,
                Animation.RELATIVE_TO_SELF,
                pivotY ?: 0.5f)
        rotate.duration = duration ?: 800
        rotate.interpolator = LinearInterpolator()
        rotate.repeatCount = repeatCount ?: Animation.INFINITE
        rotate.repeatMode = if (reverse != null && reverse) Animation.REVERSE else Animation.RESTART
        rotate.startOffset = startOffset ?: 0

        view.startAnimation(rotate)
    } else {
        view.animation = null
    }
}

/**
 *
 * Add a bounce animation to the view, that scale down it when the user touch inside and scale up
 * when the user loose the focus on it
 *
 * @param view The view that need the animation
 * @param enabled Set it to true to enable the bounce for the view
 * @param scaleX The x scale to reach when the user has the focus on the view
 * @param scaleY The x scale to reach when the user has the focus on the view
 * @param upDuration The duration of the animation for reset the scale
 * @param downDuration The duration of the animation for scale down the view
 */
@BindingAdapter(
        "animationBounce_enable",
        "animationBounce_scaleX",
        "animationBounce_scaleY",
        "animationBounce_upDuration",
        "animationBounce_downDuration",
        requireAll = false)
fun bindBounce(
        view: View,
        enabled: Boolean?,
        scaleX: Float?,
        scaleY: Float?,
        upDuration: Long?,
        downDuration: Long?) {
    if (enabled != null && enabled) {
        view.setOnTouchListener(object : View.OnTouchListener {
            internal var touchOutside = true

            override fun onTouch(v1: View, event: MotionEvent): Boolean {
                var response = false
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {

                        //User touch the view (scale it down)
                        scaleView(
                                view,
                                scaleX ?: .9f,
                                scaleY ?: 9f,
                                downDuration ?: 100)
                        touchOutside = false
                        response = true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val rect = Rect(view.left, view.top, view.right, view.bottom)
                        if (!rect.contains(view.left + event.x.toInt(), view.top + event.y.toInt()) && !touchOutside) {

                            // User moved outside bounds (reset the scale)
                            touchOutside = true
                            scaleView(
                                    view,
                                    1f,
                                    1f,
                                    upDuration ?: 100)
                        } else if (rect.contains(view.left + event.x.toInt(), view.top + event.y.toInt()) && touchOutside) {

                            //User moved inside bounds (scale it down)
                            scaleView(
                                    view,
                                    scaleX ?: .9f,
                                    scaleY ?: .9f,
                                    downDuration ?: 100)
                            touchOutside = false
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {

                        // User cancel the touch (reset the scale)
                        scaleView(
                                view,
                                1f,
                                1f,
                                upDuration ?: 100)
                        touchOutside = true
                    }
                    MotionEvent.ACTION_UP -> {

                        // User release the view (reset the scale)
                        scaleView(
                                view,
                                1f,
                                1f,
                                upDuration ?: 100)
                        if (!touchOutside) {
                            view.performClick()
                        }
                        touchOutside = true
                    }
                }
                return response
            }
        })
    } else {
        view.setOnTouchListener(null)
    }
}

/**
 *
 * Animate the view for reach the target scale
 *
 * @param view The view that need the scale animation
 * @param scaleX The target x scale for the animation
 * @param scaleY The target y scale for the animation
 * @param duration The duration of the animation (milliseconds)
 */
fun scaleView(view: View, scaleX: Float, scaleY: Float, duration: Long) {
    view.animate()
            .scaleX(scaleX)
            .scaleY(scaleY)
            .duration = duration
}