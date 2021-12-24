package com.example.cinema.features

import android.os.Bundle
import com.example.cinema.moxy.MvpAndroidxActivity

abstract class BaseActivity : MvpAndroidxActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun getContainerId(): Int
}