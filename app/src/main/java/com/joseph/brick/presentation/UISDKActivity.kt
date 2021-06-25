package com.joseph.brick.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blankj.utilcode.util.ToastUtils
import com.joseph.brick.R
import com.joseph.brick.databinding.ActivityMainBinding

// import io.onebrick.sdk.*
// import io.onebrick.sdk.util.Environment

class UISDKActivity :
    AppCompatActivity(R.layout.activity_main),
    View.OnClickListener {
    //    ICoreBrickUISDK
    private val mBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.title = "Brick Beta Testing"
        mBinding.listener = this
    }

    override fun onClick(v: View?) {
        when (v) {
            mBinding.btnStart -> {
                ToastUtils.showShort("Show Core UI Brick")
                // TODO Integrate with Brick Core UI
//                CoreBrickUISDK.initializedUISDK( applicationContext,
//                    clientId,
//                    clientSecret,
//                    name, url,
//                    coreBrickUIDelegate!!,
//                    Environment.SANDBOX)
            }
        }
    }
}
