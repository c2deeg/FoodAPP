package com.app.marier.fragments.OnBoardSplashFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.marier.R
import com.app.marier.databinding.FragmentOnBoardSplashBinding


class OnBoardSplashFragment : Fragment(), View.OnClickListener {
    private var binding:FragmentOnBoardSplashBinding?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardSplashBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        listeners()
        return view
    }

    private fun listeners(){
        binding?.tvphone?.setOnClickListener(this)
        binding?.tvsignup?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvphone->findNavController().navigate(R.id.action_onBoardSplashFragment_to_myPhoneNumberFragment)
            R.id.tvsignup->findNavController().navigate(R.id.action_onBoardSplashFragment_to_signupFragment)

        }

    }


}