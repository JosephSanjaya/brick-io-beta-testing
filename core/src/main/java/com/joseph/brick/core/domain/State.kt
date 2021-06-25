/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.domain

import java.io.File

object State {
    sealed class Single<T> {
        class Idle<T> : Single<T>()
        class Loading<T> : Single<T>()
        data class Success<T>(val data: T) : Single<T>()
        data class Failed<T>(val throwable: Throwable, val data: T? = null) : Single<T>()
    }

    sealed class Download {
        object Idle : Download()
        object Loading : Download()
        data class Progress(val progress: kotlin.Double) : Download()
        data class Exist(val url: String, val data: File) : Download()
        data class Success(val data: File) : Download()
        data class Failed(val throwable: Throwable) : Download()
    }

    sealed class Double<T, K> {
        class Idle<T, K> : Double<T, K>()
        class Loading<T, K> : Double<T, K>()
        data class Success<T, K>(val data1: T, val data2: K) : Double<T, K>()
        data class Failed<T, K>(
            val throwable: Throwable,
            val data1: T? = null,
            val data2: K? = null
        ) : Double<T, K>()
    }

    sealed class Triple<T, K, V> {
        class Idle<T, K, V> : Triple<T, K, V>()
        class Loading<T, K, V> : Triple<T, K, V>()
        data class Success<T, K, V>(val data1: T, val data2: K, val data3: V) : Triple<T, K, V>()
        data class Failed<T, K, V>(
            val throwable: Throwable,
            val data1: T? = null,
            val data2: K? = null,
            val data3: V? = null
        ) : Triple<T, K, V>()
    }

    sealed class Quad<T1, T2, T3, T4> {
        class Idle<T1, T2, T3, T4> : Quad<T1, T2, T3, T4>()
        class Loading<T1, T2, T3, T4> : Quad<T1, T2, T3, T4>()
        data class Success<T1, T2, T3, T4>(
            val data1: T1,
            val data2: T2,
            val data3: T3,
            val data4: T4
        ) : Quad<T1, T2, T3, T4>()

        data class Failed<T1, T2, T3, T4>(
            val throwable: Throwable,
            val data1: T1? = null,
            val data2: T2? = null,
            val data3: T3? = null,
            val data4: T4? = null
        ) : Quad<T1, T2, T3, T4>()
    }
}
