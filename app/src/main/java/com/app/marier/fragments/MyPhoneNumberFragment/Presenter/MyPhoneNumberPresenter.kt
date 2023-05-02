package com.app.marier.fragments.MyPhoneNumberFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.marier.Handler.LoginOtpHandler
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.fragments.MyPhoneNumberFragment.MyPhoneNumberFragment
import com.app.marier.fragments.MyPhoneNumberFragment.View.MyPhoneNumberView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.app.marier.Models.Loginotp.LoginExample
import com.app.marier.Utils.CSPreferences


class MyPhoneNumberPresenter(
    private val activity: FragmentActivity,
   private val myPhoneNumberFragment: MyPhoneNumberFragment,
   private val myPhoneNumberView: MyPhoneNumberView
) {



    fun loginotp(phonenum:String) {
        if (Utils.isNetworkConnected(activity!!)) {
            myPhoneNumberView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.loginMethod(phonenum, object :
                LoginOtpHandler {
                override fun onSuccess(loginExample: LoginExample?) {
                    if (loginExample!=null){
                        CSPreferences.putString(activity,Utils.TOKEN,loginExample.data.token)
                        myPhoneNumberView.hideDialog()
                        findNavController(myPhoneNumberFragment).navigate(R.id.action_myPhoneNumberFragment_to_enterotpFragment)
                        myPhoneNumberView.showMessage(activity,loginExample?.message)
                    }else{
                        myPhoneNumberView.hideDialog()
                        Toast.makeText(activity, "something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(message: String?) {
                    myPhoneNumberView.hideDialog()
                    myPhoneNumberView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }




}