/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.presentation.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.joseph.brick.R
import com.joseph.brick.core.data.accessToken
import com.joseph.brick.databinding.ActivityMainBinding
import com.joseph.brick.presentation.fragment.StartFragment
import com.joseph.brick.utils.replaceFragment
import org.koin.android.ext.android.inject

class MainActivity :
    AppCompatActivity(R.layout.activity_main) {
    private val mBinding by viewBinding(ActivityMainBinding::bind)
    private val mSharedPreferences by inject<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.title = "Brick Beta Testing"
        mSharedPreferences.initChangeFragment()
    }

    private fun SharedPreferences.initChangeFragment() {
        replaceFragment(StartFragment(), isAnimate = true)
        // TODO if (accessToken != null)
    }
}
