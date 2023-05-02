package com.app.marier.fragments.ProfileFragment.View

import android.app.Activity

interface ProfileView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}