package com.app.marier.fragments.EnterotpFragment.View

import android.app.Activity

interface EnterotpView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}