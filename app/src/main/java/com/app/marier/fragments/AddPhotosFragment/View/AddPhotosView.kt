package com.app.marier.fragments.AddPhotosFragment.View

import android.app.Activity

interface AddPhotosView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}