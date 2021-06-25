/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.joseph.brick.core.domain.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

//class AuthenticationRepository(
//    private val mSecuredPref: SharedPreferences
//) {
//
//    @ExperimentalCoroutinesApi
//    suspend fun requestAuthentication() = callbackFlow {
//        trySend(State.Single.Loading())
//        CoreBrickSDK.requestAccessToken(object : IAccessTokenRequestResult {
//            override fun success(accessToken: AccessToken?) {
//                mSecuredPref.accessToken = accessToken
//                trySend(State.Single.Success(accessToken))
//            }
//
//            override fun error(t: Throwable?) {
//                throw t
//            }
//        })
//        awaitClose()
//    }
//}
