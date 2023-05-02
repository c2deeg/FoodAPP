package com.app.marier.fragments.SignupFragment.View

import android.app.Activity

interface SignupView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}