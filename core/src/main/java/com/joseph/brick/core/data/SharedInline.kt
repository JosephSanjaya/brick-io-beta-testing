/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import io.onebrick.sdk.model.AccessToken

inline var SharedPreferences.accessToken: AccessToken?
    get() = getString("access_token", null)?.let {
        Gson().fromJson(it, AccessToken::class.java)
    }
    set(value) = set("access_token", Gson().toJson(value))

fun SharedPreferences.copyTo(dest: SharedPreferences) {
    for (entry in all.entries) {
        val key = entry.key
        val value: Any? = entry.value
        dest.set(key, value)
    }
}

fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> edit { putString(key, value) }
        is Int -> edit { putInt(key, value.toInt()) }
        is Boolean -> edit { putBoolean(key, value) }
        is Float -> edit { putFloat(key, value.toFloat()) }
        is Long -> edit { putLong(key, value.toLong()) }
        else -> {
            Logger.e("Unsupported Type: $value")
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> SharedPreferences.get(key: String, default: T?): T? {
    return when (default) {
        is String? -> getString(key, default) as T
        is Int -> getInt(key, default) as T
        is Boolean -> getBoolean(key, default) as T
        is Float -> getFloat(key, default) as T
        is Long -> getLong(key, default) as T
        else -> {
            null
        }
    }
}

fun SharedPreferences.clear() {
    edit { clear() }
}

fun SharedPreferences.remove(key: String) {
    edit { remove(key) }
}
