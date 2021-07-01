package com.joseph.brick

import androidx.multidex.MultiDexApplication
import coil.load
import com.joseph.brick.core.di.CoreModules
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.yuyh.library.imgsel.ISNav
import io.onebrick.sdk.CoreBrickSDK
import io.onebrick.sdk.util.Environment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BrickApp : MultiDexApplication() {
    override fun onCreate() {
        Logger.addLogAdapter(AndroidLogAdapter())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BrickApp)
            modules(
                CoreModules
                // Add Module DI
            )
        }
        initSDK()
//        enableEmulator()
        ISNav.getInstance().init { _, path, imageView -> imageView?.load(path) }
        super.onCreate()
    }

    private fun initSDK() {
        CoreBrickSDK.initializedSDK(
            BuildConfig.BRICK_CLIENT_ID,
            BuildConfig.BRICK_SECRET,
            BuildConfig.BRICK_NAME,
            BuildConfig.BRICK_URL,
            Environment.SANDBOX
        )
    }
}
