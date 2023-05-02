package com.app.marier.activities.UserDetailActivity.View

import android.app.Activity
import com.app.marier.Models.GetCurrentUser.Data
import com.app.marier.activities.UserDetailActivity.UserDetailActivity

interface UserDetailView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun getData(activity: UserDetailActivity, data: Data)
}