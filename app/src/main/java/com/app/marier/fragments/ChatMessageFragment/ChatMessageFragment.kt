package com.app.marier.fragments.ChatMessageFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.marier.R
import com.app.marier.adapter.ChatListRecyclerAdapter
import com.app.marier.databinding.FragmentChatMessageBinding
import com.app.marier.databinding.FragmentMessageBinding


class ChatMessageFragment : Fragment() {
    private var binding:FragmentChatMessageBinding?=null
    private var chatListRecyclerAdapter:ChatListRecyclerAdapter?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatMessageBinding.inflate(layoutInflater,container,false)
        val view:View = binding!!.root

        listeners()
        initRecylerview()
        return view
    }

    private fun listeners(){

    }
    private fun initRecylerview(){
        chatListRecyclerAdapter = ChatListRecyclerAdapter(activity as FragmentActivity)
        binding!!.chatlistrecyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding!!.chatlistrecyclerview.adapter = chatListRecyclerAdapter
    }
}