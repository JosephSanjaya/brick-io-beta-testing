/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.presentation.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blankj.utilcode.util.ToastUtils
import com.joseph.brick.BuildConfig
import com.joseph.brick.R
import com.joseph.brick.core.presentation.AuthenticationObserver
import com.joseph.brick.core.presentation.AuthenticationViewModel
import com.joseph.brick.databinding.FragmentStartBinding
import com.joseph.brick.utils.appCompatActivity
import com.joseph.brick.utils.makeLoadingDialog
import com.joseph.brick.utils.replaceFragment
import io.onebrick.sdk.CoreBrickUISDK
import io.onebrick.sdk.ICoreBrickUISDK
import io.onebrick.sdk.model.AccessToken
import io.onebrick.sdk.model.AuthenticateUserResponseData
import io.onebrick.sdk.util.Environment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment :
    Fragment(R.layout.fragment_start),
    View.OnClickListener,
    ICoreBrickUISDK,
    AuthenticationObserver.Interfaces {
    private val mBinding by viewBinding(FragmentStartBinding::bind)
    private val mViewModel by viewModel<AuthenticationViewModel>()
    private val mLoading by lazy {
        requireContext().makeLoadingDialog(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLifecycleOwner.lifecycle.addObserver(
            AuthenticationObserver(this, mViewModel, viewLifecycleOwner)
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.listener = this
    }

    override fun onClick(v: View?): Unit = with(mBinding) {
        when (v) {
            btnAccessToken -> mViewModel.getAccessToken()
            else -> CoreBrickUISDK.initializedUISDK(
                requireContext(),
                BuildConfig.BRICK_CLIENT_ID,
                BuildConfig.BRICK_SECRET,
                BuildConfig.BRICK_NAME,
                BuildConfig.BRICK_URL,
                this@StartFragment,
                Environment.SANDBOX
            )
        }
    }

    override fun onRequestAccessTokenLoading() {
        mLoading.show()
        super.onRequestAccessTokenLoading()
    }

    override fun onRequestAccessTokenSuccess(accessToken: AccessToken?) {
        mLoading.dismiss()
        appCompatActivity?.replaceFragment(
            InsitutionFragment(),
            isAnimate = true,
            isInclusive = true
        )
        super.onRequestAccessTokenSuccess(accessToken)
    }

    override fun onRequestAccessTokenFailed(e: Throwable) {
        mLoading.dismiss()
        ToastUtils.showShort(e.message)
        super.onRequestAccessTokenFailed(e)
    }

    override fun onTransactionSuccess(transactionResult: AuthenticateUserResponseData) {
        ToastUtils.showShort(transactionResult.accessToken)
    }
}
