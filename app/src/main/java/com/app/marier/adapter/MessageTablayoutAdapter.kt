package com.app.marier.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.marier.fragments.ChatMessageFragment.ChatMessageFragment
import com.app.marier.fragments.HomeFragment.HomeFragment
import com.app.marier.fragments.MessageFragment.MessageFragment
import com.app.marier.fragments.ProfileFragment.ProfileFragment
import com.app.marier.fragments.VideocallFragment.VideocallFragment

class MessageTablayoutAdapter (private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return ChatMessageFragment()
            }
            1 -> {
                return VideocallFragment()
            }

            else -> return VideocallFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}