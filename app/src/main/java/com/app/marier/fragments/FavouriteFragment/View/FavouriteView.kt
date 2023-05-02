package com.app.marier.fragments.FavouriteFragment.View

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.app.marier.Models.GetLikesModel.Data

interface FavouriteView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}