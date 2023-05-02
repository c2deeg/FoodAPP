package com.app.marier.fragments.MyPhoneNumberFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.databinding.FragmentMyPhoneNumberBinding
import com.app.marier.fragments.MyPhoneNumberFragment.Presenter.MyPhoneNumberPresenter
import com.app.marier.fragments.MyPhoneNumberFragment.View.MyPhoneNumberView


class MyPhoneNumberFragment : Fragment(), View.OnClickListener,MyPhoneNumberView {
    private var binding:FragmentMyPhoneNumberBinding?=null
    private var myPhoneNumberPresenter:MyPhoneNumberPresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPhoneNumberBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        listeners()
        myPhoneNumberPresenter = MyPhoneNumberPresenter(requireActivity(),this,this)
        return view
    }
    private fun listeners(){
        binding?.tvcontinue?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvcontinue->{
                myPhoneNumberPresenter?.loginotp(binding?.etphonennum?.text.toString())

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