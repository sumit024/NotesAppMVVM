package com.app_devs.noteit

import android.text.TextUtils

object Constants {
    fun verifyInput(s1:String, s2:String, s3:String):Boolean
    {
        return !(TextUtils.isEmpty(s1)&&TextUtils.isEmpty(s2)&&TextUtils.isEmpty(s3))
    }
}