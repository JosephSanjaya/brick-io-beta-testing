/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.data

import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//inline var SharedPreferences.accessToken: AccessToken?
//    get() = getString("access_token", null)?.let {
//        Json.decodeFromString(it)
//    }
//    set(value) = set("access_token", Json.encodeToString(value))