package com.app.marier.fragments.EnterotpFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.marier.Handler.LoginOtpHandler
import com.app.marier.Handler.OtpVerificationHandler
import com.app.marier.Models.Loginotp.LoginExample
import com.app.marier.Models.OtpVerifcation.OtpVerficationExample
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.fragments.EnterotpFragment.EnterotpFragment
import com.app.marier.fragments.EnterotpFragment.View.EnterotpView

class EnterotpPresenter(
   private val activity: FragmentActivity,
   private val enterotpView: EnterotpView,
    private val enterotpFragment: EnterotpFragment
) {

    fun enteroptMethod( otp:String) {
        var token = CSPreferences.readString(activity,Utils.TOKEN)
        if (Utils.isNetworkConnected(activity!!)) {
            enterotpView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.otpverificationMethod(token!!,otp, object :
                OtpVerificationHandler {
                override fun onSuccess(otpVerficationExample: OtpVerficationExample?) {
                    if (otpVerficationExample!=null){
                        enterotpView.hideDialog()
                        NavHostFragment.findNavController(enterotpFragment).navigate(R.id.action_enterotpFragment_to_locationFragment)
                        enterotpView.showMessage(activity,otpVerficationExample?.message)
                        CSPreferences.putString(activity,Utils.USERID,otpVerficationExample.data.id)
                        CSPreferences.putString(activity,Utils.TOKEN,otpVerficationExample.token)
                    }else{
                        enterotpView.hideDialog()
                        enterotpView.showMessage(activity,otpVerficationExample?.message)

                    }
                }

                override fun onError(message: String?) {
                    enterotpView.hideDialog()
                    enterotpView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

}