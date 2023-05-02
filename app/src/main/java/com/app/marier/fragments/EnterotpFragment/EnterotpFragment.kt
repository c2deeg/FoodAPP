package com.app.marier.fragments.EnterotpFragment

import `in`.aabhasjindal.otptextview.OTPListener
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.databinding.FragmentEnterotpBinding
import com.app.marier.fragments.EnterotpFragment.Presenter.EnterotpPresenter
import com.app.marier.fragments.EnterotpFragment.View.EnterotpView


class EnterotpFragment : Fragment(), View.OnClickListener,EnterotpView {
    private var binding:FragmentEnterotpBinding?=null
    private var enterotpPresenter:EnterotpPresenter?=null
    private var otpval:String?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEnterotpBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        listeners()
        enterotpPresenter = EnterotpPresenter(requireActivity(),this,this)

        binding!!.otpview.setOtpListener(object : OTPListener {
            override fun onInteractionListener() {
                // fired when user types something in the Otpbox
            }

            override fun onOTPComplete(otp: String) {
                // fired when user has entered the OTP fully.
                Toast.makeText(activity, "The OTP is $otp", Toast.LENGTH_SHORT).show()
                otpval = otp
            }
        })
        return view
    }
    private fun listeners(){
        binding?.tvcontinue?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvcontinue->{
                enterotpPresenter?.enteroptMethod(otpval!!)
//                findNavController().navigate(R.id.action_enterotpFragment_to_sexualOrientationFragment)
            }

        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity,msg)

    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity,activity?.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

}