package com.app.marier.fragments.MyPhoneNumberFragment.View

import android.app.Activity

interface MyPhoneNumberView  {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}