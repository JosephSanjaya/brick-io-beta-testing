/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.core.di

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.joseph.brick.core.data.AuthenticationRepository
import com.joseph.brick.core.data.InsitutionRepository
import com.joseph.brick.core.presentation.AuthenticationViewModel
import com.joseph.brick.core.presentation.InsitutionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val CoreModules = module {
    single {
        MasterKey.Builder(androidApplication())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }
    single {
        EncryptedSharedPreferences.create(
            androidApplication(),
            "BRICK",
            get(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    single {
        AuthenticationRepository(get())
    }
    viewModel {
        AuthenticationViewModel(get())
    }
    single {
        InsitutionRepository()
    }
    viewModel {
        InsitutionViewModel(get())
    }
}
