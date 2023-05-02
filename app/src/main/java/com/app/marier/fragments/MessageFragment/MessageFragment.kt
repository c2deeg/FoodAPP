package com.app.marier.fragments.MessageFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.marier.R
import com.app.marier.adapter.ChatListRecyclerAdapter
import com.app.marier.adapter.MessageTablayoutAdapter
import com.app.marier.databinding.FragmentMessageBinding
import com.google.android.material.tabs.TabLayout


class MessageFragment : Fragment() {
    private var binding: FragmentMessageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(layoutInflater, container, false)
        val view: View = binding!!.root
        inittablayout()
        return view
    }

    private fun inittablayout() {
        binding?.tabLayout!!.addTab(binding?.tabLayout!!.newTab().setText("Message"))
        binding?.tabLayout!!.addTab(binding?.tabLayout!!.newTab().setText("Video call"))

        binding?.tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MessageTablayoutAdapter(
            requireActivity(),
            childFragmentManager, binding?.tabLayout!!.tabCount
        )
        binding?.viewPager!!.adapter = adapter

        binding?.viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding?.tabLayout))

        binding?.tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding?.viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


}