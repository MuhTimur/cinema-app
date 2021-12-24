package com.example.cinema.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinema.moxy.MvpAndroidxFragment

abstract class BaseFragment : MvpAndroidxFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(getLayoutId(), container, false)
    }

    protected abstract fun getLayoutId(): Int
}
