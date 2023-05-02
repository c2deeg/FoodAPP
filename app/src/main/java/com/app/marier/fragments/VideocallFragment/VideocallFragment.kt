package com.app.marier.fragments.VideocallFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.marier.R
import com.app.marier.databinding.FragmentChatMessageBinding
import com.app.marier.databinding.FragmentVideocallBinding


class VideocallFragment : Fragment() {
    private var binding:FragmentVideocallBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVideocallBinding.inflate(layoutInflater,container,false)
        val view:View = binding!!.root

//        listeners()
//        initRecylerview()
        return view
    }

}