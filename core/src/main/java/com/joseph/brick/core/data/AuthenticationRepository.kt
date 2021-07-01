/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.data

import android.content.SharedPreferences
import com.joseph.brick.core.domain.State
import io.onebrick.sdk.CoreBrickSDK
import io.onebrick.sdk.IAccessTokenRequestResult
import io.onebrick.sdk.IRequestResponseUserAuth
import io.onebrick.sdk.IRequestTransactionResult
import io.onebrick.sdk.model.AccessToken
import io.onebrick.sdk.model.AuthenticateUserResponse
import io.onebrick.sdk.model.AuthenticateUserResponseData
import io.onebrick.sdk.model.MFABankingPayload
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class AuthenticationRepository(
    private val mSecuredPref: SharedPreferences
) {

    @ExperimentalCoroutinesApi
    suspend fun requestAuthentication() = callbackFlow {
        trySend(State.Single.Loading())
        CoreBrickSDK.requestAccessToken(object : IAccessTokenRequestResult {
            override fun success(accessToken: AccessToken?) {
                mSecuredPref.accessToken = accessToken
                trySend(State.Single.Success(mSecuredPref.accessToken))
                close()
            }

            override fun error(t: Throwable?) {
                t ?: Throwable("Failed to get Access Token").let {
                    trySend(State.Single.Failed(it))
                    close(it)
                }
            }
        })
        awaitClose()
    }

    @ExperimentalCoroutinesApi
    suspend fun requestBankAuthentication(
        username: String,
        password: String,
        institutionId: String
    ) = callbackFlow {
        trySend(State.Bank.Loading())
        CoreBrickSDK.authenticateUser(
            username,
            password,
            institutionId,
            object : IRequestResponseUserAuth {
                override fun error(t: Throwable?) {
                    t ?: Throwable("Failed to get Access!").let {
                        trySend(State.Bank.Failed(it))
                        close(it)
                    }
                }

                override fun success(response: AuthenticateUserResponse) {
                    when (response.status) {
                        MFAA_NEEDED -> {
                            trySend(State.Bank.MFAA(response.data))
                            close()
                        }
                        else -> {
                            trySend(State.Bank.Success(response.data))
                            close()
                        }
                    }
                }
            }
        )
        awaitClose()
    }

    @ExperimentalCoroutinesApi
    suspend fun requestMFAA(
        auth: AuthenticateUserResponseData
    ) = callbackFlow {
        trySend(State.Single.Loading())
        val payload = MFABankingPayload(
            token = auth.otpToken,
            duration = auth.duration,
            institutionId = auth.bankId?.toLongOrNull() ?: 0L,
            redirectRefId = "you will get the response from the previous step",
            sessionId = "you will get the response from the previous step",
            username = "you will get the response from the previous step",
            password = "you will get the response from the previous step"
        )
        CoreBrickSDK.submitCredentialsForMFAAccount(
            payload,
            object : IRequestTransactionResult {
                override fun error(t: Throwable?) {
                    t ?: Throwable("Failed to get Access!").let {
                        trySend(State.Single.Failed(it))
                        close(it)
                    }
                }

                override fun success(response: AuthenticateUserResponse?) {
                    trySend(State.Single.Success(response?.data))
                    close()
                }
            }
        )
        awaitClose()
    }

    @ExperimentalCoroutinesApi
    suspend fun authenticateEWallet(
        auth: AuthenticateUserResponseData
    ) = callbackFlow {
        trySend(State.Single.Loading())
        val payload = MFABankingPayload(
            username = "you will get the response from the previous step",
            institutionId = 0L,
            redirectRefId = "you will get the response from the previous step"
        )
        CoreBrickSDK.authenticateEwalletUser(
            payload,
            object : IRequestTransactionResult {
                override fun success(credentials: AuthenticateUserResponse?) {
                    trySend(State.Single.Success(credentials?.data))
                    close()
                }

                override fun error(t: Throwable?) {
                    t ?: Throwable("Failed to get Access!").let {
                        trySend(State.Single.Failed(it))
                        close(it)
                    }
                }
            }
        )
        awaitClose()
    }

    companion object {
        const val MFAA_NEEDED = 428L
    }
}
