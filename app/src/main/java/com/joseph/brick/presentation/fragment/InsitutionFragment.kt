/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blankj.utilcode.util.ToastUtils
import com.joseph.brick.R
import com.joseph.brick.core.presentation.InsitutionObserver
import com.joseph.brick.core.presentation.InsitutionViewModel
import com.joseph.brick.databinding.FragmentInsitutionBinding
import com.joseph.brick.presentation.adapter.InsitutionAdapter
import io.onebrick.sdk.model.InstitutionData
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsitutionFragment :
    Fragment(R.layout.fragment_insitution),
    InsitutionObserver.Interfaces,
    SwipeRefreshLayout.OnRefreshListener {
    private val mBinding by viewBinding(FragmentInsitutionBinding::bind)
    private val mViewModel by viewModel<InsitutionViewModel>()
    private val isLoading = ObservableBoolean(false)

    private val mData = mutableListOf<InstitutionData>()
    private val mAdapter by lazy {
        InsitutionAdapter(mData)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLifecycleOwner.lifecycle.addObserver(
            InsitutionObserver(
                this,
                mViewModel,
                viewLifecycleOwner
            )
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.initBinding()
        onRefresh()
    }

    private fun FragmentInsitutionBinding.initBinding() {
        rvContent.adapter = mAdapter
        isLoading = this@InsitutionFragment.isLoading
    }

    override fun onRefresh() {
        mViewModel.getInsitution()
    }

    override fun onFetchInsitutionLoading() {
        isLoading.set(true)
        super.onFetchInsitutionLoading()
    }

    override fun onFetchInsitutionSuccess(insitution: List<InstitutionData>?) {
        mAdapter.setNewInstance(insitution?.toMutableList())
        isLoading.set(false)
        super.onFetchInsitutionSuccess(insitution)
    }

    override fun onFetchInsitutionFailed(e: Throwable) {
        isLoading.set(false)
        ToastUtils.showShort(e.message)
        super.onFetchInsitutionFailed(e)
    }
}
