/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.utils

import android.content.ActivityNotFoundException
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.joseph.brick.R
import com.orhanobut.logger.Logger

inline val Fragment.appCompatActivity
    get() = if (activity is AppCompatActivity)
        activity as AppCompatActivity
    else
        null

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    isBackstack: Boolean = false,
    isAnimate: Boolean = false,
    isInclusive: Boolean = false,
    toolbarChange: ((ActionBar?) -> Unit)? = null
) {
    try {
        supportFragmentManager.replaceFragment(
            R.id.flFragment,
            fragment,
            isBackstack,
            isAnimate,
            isInclusive
        )
        toolbarChange?.invoke(supportActionBar)
    } catch (e: ActivityNotFoundException) {
        Logger.e(e, e.message.toString())
    }
}

fun FragmentActivity.popBackStack() {
    supportFragmentManager.popBackStack()
}

fun AppCompatActivity.setToolbar(
    title: String,
    isBack: Boolean,
    isHide: Boolean,
) {
    supportActionBar?.setToolbar(title, isBack, isHide)
}

fun ActionBar.setToolbar(
    title: String,
    isBack: Boolean,
    isHide: Boolean,
) {
    elevation = 0f
    setTitle(title)
    setDisplayHomeAsUpEnabled(isBack)
    if (isHide) hide() else show()
}

fun FragmentManager.replaceFragment(
    placeholder: Int,
    fragment: Fragment,
    isBackstack: Boolean = false,
    isAnimate: Boolean = false,
    isInclusive: Boolean = false
) {
    beginTransaction().apply {
        if (isBackstack) addToBackStack(null)
        if (isAnimate) setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        if (isInclusive) popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        replace(placeholder, fragment)
    }.commit()
}
