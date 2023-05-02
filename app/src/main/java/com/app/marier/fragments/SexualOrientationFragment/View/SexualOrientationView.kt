package com.app.marier.fragments.SexualOrientationFragment.View

import android.app.Activity

interface SexualOrientationView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}