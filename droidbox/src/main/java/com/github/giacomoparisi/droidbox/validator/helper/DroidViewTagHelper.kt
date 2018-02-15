package com.github.giacomoparisi.droidbox.validator.helper

import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.github.giacomoparisi.droidbox.validator.rule.core.DroidValidatorRule
import java.util.*

/**
 * Created by Giacomo Parisi on 29/01/18.
 * https://github.com/giacomoParisi
 */

object DroidViewTagHelper {

    fun appendValue(tagId: Int, view: View, value: DroidValidatorRule<*, *>) {
        val obj = view.getTag(tagId)
        if (obj != null && obj is MutableList<*> && obj.size > 0 && obj[0] is DroidValidatorRule<*, *>) {
            (obj as MutableList<DroidValidatorRule<*, *>>).add(value)
        } else {
            val typeList = ArrayList<DroidValidatorRule<*, *>>()
            typeList.add(value)
            view.setTag(tagId, typeList)
        }
    }

    fun getViewsByTag(root: ViewGroup, tagId: Int): List<View> {
        val views = ArrayList<View>()
        val childCount = root.childCount
        for (i in 0 until childCount) {
            val child = root.getChildAt(i)
            if (child is ViewGroup && child.visibility == VISIBLE) {
                views.addAll(getViewsByTag(child, tagId))
            }
            addViewWhenContainsTag(tagId, views, child)
        }
        return views
    }

    fun filterViewWithTag(tagId: Int, view: View): List<View> {
        val viewsWithTags = ArrayList<View>()
        addViewWhenContainsTag(tagId, viewsWithTags, view)
        return viewsWithTags
    }

    fun <ViewType : View> filterViewsWithTag(tagId: Int, views: List<ViewType>): List<View> {
        val viewsWithTags = ArrayList<View>()
        for (view in views) {
            addViewWhenContainsTag(tagId, viewsWithTags, view)
        }
        return viewsWithTags
    }

    private fun addViewWhenContainsTag(tagId: Int, views: MutableList<View>, view: View) {
        val tagValue = view.getTag(tagId)
        if (tagValue != null && view.visibility == VISIBLE) {
            views.add(view)
        }
    }
}

