package com.app.marier.fragments.LikeFragment.View

import android.app.Activity

interface LikeView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}