/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.joseph.brick.presentation.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.joseph.brick.R
import com.joseph.brick.databinding.RowInstitutionBinding
import io.onebrick.sdk.model.InstitutionData

class InsitutionAdapter(data: MutableList<InstitutionData>?) :
    BaseQuickAdapter<InstitutionData, BaseDataBindingHolder<RowInstitutionBinding>>
    (R.layout.row_institution, data) {

    init {
        animationEnable = false
    }

    override fun convert(
        holder: BaseDataBindingHolder<RowInstitutionBinding>,
        item: InstitutionData
    ) {
        holder.dataBinding?.apply {
            tvInstitution.text = item.bankName
        }
    }
}
