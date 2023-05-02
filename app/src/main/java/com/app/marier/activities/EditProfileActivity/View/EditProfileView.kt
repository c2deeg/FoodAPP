package com.app.marier.activities.EditProfileActivity.View

import android.app.Activity
import com.app.marier.Models.GetCurrentUser.Data
import com.app.marier.activities.EditProfileActivity.EditProfileActivity

interface EditProfileView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun getdata(activity: EditProfileActivity, data: Data?)
}