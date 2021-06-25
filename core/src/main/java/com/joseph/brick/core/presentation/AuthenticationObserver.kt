/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.presentation

import androidx.lifecycle.*
import com.joseph.brick.core.domain.State
import kotlinx.coroutines.launch

//class AuthenticationObserver(
//    private val view: Interfaces,
//    private val viewModel: AuthenticationViewModel,
//    private val owner: LifecycleOwner
//) : LifecycleObserver {
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    fun onCreate() {
//        owner.lifecycleScope.launch {
//            viewModel.mAccessToken.collect {
//                when (it) {
//                    is State.Single.Idle -> view.onRequestAccessTokenIdle()
//                    is State.Single.Loading -> view.onRequestAccessTokenLoading()
//                    is State.Single.Success -> {
//                        view.onRequestAccessTokenSuccess(it.data)
//                        viewModel.resetAccessTokenState()
//                    }
//                    is State.Single.Failed -> {
//                        view.onRequestAccessTokenFailed(it.throwable)
//                        viewModel.resetAccessTokenState()
//                    }
//                }
//            }
//        }
//    }
//
//    interface Interfaces {
//        fun onRequestAccessTokenIdle() {
//            // Do Nothing
//        }
//
//        fun onRequestAccessTokenLoading() {
//            // Do Nothing
//        }
//
//        fun onRequestAccessTokenSuccess(accessToken: AccessToken) {
//            // Do Nothing
//        }
//
//        fun onRequestAccessTokenFailed(e: Throwable) {
//            // Do Nothing
//        }
//    }
//}
