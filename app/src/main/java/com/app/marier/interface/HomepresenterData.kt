package com.app.marier.`interface`

import androidx.fragment.app.FragmentActivity
import com.app.marier.Models.GetRandomList.Data

interface HomepresenterData {
    fun getdata(activity: FragmentActivity, data: List<Data>)
}