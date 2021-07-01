/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.presentation

import com.joseph.brick.core.data.AuthenticationRepository
import com.joseph.brick.core.domain.State
import io.onebrick.sdk.model.AccessToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val mRepository: AuthenticationRepository
) : BaseViewModel() {
    private val _accesToken = MutableStateFlow<State.Single<AccessToken?>>(State.Single.Idle())
    val mAccessToken: StateFlow<State.Single<AccessToken?>> get() = _accesToken

    fun resetAccessTokenState() {
        _accesToken.value = State.Single.Idle()
    }

    fun getAccessToken() = ioScope.launch {
        mRepository.requestAuthentication()
            .catch {
                _accesToken.emit(State.Single.Failed(it))
            }
            .collect {
                _accesToken.emit(it)
            }
    }
}
