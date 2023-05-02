package com.app.marier.`interface`

import androidx.fragment.app.FragmentActivity
import com.app.marier.Models.GetCurrentUser.Data

interface ProfileCurrentUser {
    fun getData(activity: FragmentActivity, data: Data)
}