/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.presentation

import androidx.lifecycle.*
import com.joseph.brick.core.domain.State
import io.onebrick.sdk.model.InstitutionData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class InsitutionObserver(
    private val view: Interfaces,
    private val viewModel: InsitutionViewModel,
    private val owner: LifecycleOwner
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        owner.lifecycleScope.launch {
            viewModel.mInsitution.collect {
                when (it) {
                    is State.Single.Idle -> view.onFetchInsitutionIdle()
                    is State.Single.Loading -> view.onFetchInsitutionLoading()
                    is State.Single.Success -> {
                        view.onFetchInsitutionSuccess(it.data)
                        viewModel.resetInsitutionState()
                    }
                    is State.Single.Failed -> {
                        view.onFetchInsitutionFailed(it.throwable)
                        viewModel.resetInsitutionState()
                    }
                }
            }
        }
    }

    interface Interfaces {
        fun onFetchInsitutionIdle() {
            // Do Nothing
        }

        fun onFetchInsitutionLoading() {
            // Do Nothing
        }

        fun onFetchInsitutionSuccess(insitution: List<InstitutionData>?) {
            // Do Nothing
        }

        fun onFetchInsitutionFailed(e: Throwable) {
            // Do Nothing
        }
    }
}
