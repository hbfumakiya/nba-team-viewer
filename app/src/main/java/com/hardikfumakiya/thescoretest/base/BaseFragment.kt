package com.hardikfumakiya.thescoretest.base

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Hardik Fumakiya this class is used as base fragments for all fragments in app
 * **/
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun toast(stringID: Int) {
        (requireActivity() as BaseActivity).toast(stringID)
    }

    fun isConnected(): Boolean {
        return (requireActivity() as BaseActivity).isConnected()
    }

}