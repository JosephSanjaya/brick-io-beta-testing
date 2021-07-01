/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.data

import com.joseph.brick.core.domain.State
import io.onebrick.sdk.CoreBrickSDK
import io.onebrick.sdk.IRequestInstituion
import io.onebrick.sdk.model.Institution
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class InsitutionRepository {

    @ExperimentalCoroutinesApi
    suspend fun fetchInsitution() = callbackFlow {
        trySend(State.Single.Loading())
        CoreBrickSDK.listInstitution(object : IRequestInstituion {
            override fun success(response: Institution?) {
                trySend(State.Single.Success(response?.data))
                close()
            }

            override fun error(t: Throwable?) {
                t ?: Throwable("Failed to get Access!").let {
                    trySend(State.Single.Failed(it))
                    close(it)
                }
            }
        })
        awaitClose()
    }
}
