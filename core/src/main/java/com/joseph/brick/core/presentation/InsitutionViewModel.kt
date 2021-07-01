/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.presentation

import com.joseph.brick.core.data.InsitutionRepository
import com.joseph.brick.core.domain.State
import io.onebrick.sdk.model.InstitutionData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class InsitutionViewModel(
    private val mRepository: InsitutionRepository
) : BaseViewModel() {
    private val _insitution =
        MutableStateFlow<State.Single<List<InstitutionData>?>>(State.Single.Idle())
    val mInsitution: StateFlow<State.Single<List<InstitutionData>?>> get() = _insitution

    fun resetInsitutionState() {
        _insitution.value = State.Single.Idle()
    }

    fun getInsitution() = ioScope.launch {
        mRepository.fetchInsitution()
            .catch {
                _insitution.emit(State.Single.Failed(it))
            }
            .collect {
                _insitution.emit(it)
            }
    }
}
