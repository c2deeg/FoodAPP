package com.app.marier.fragments.InterestFragment.View

import android.app.Activity

interface InterestView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}