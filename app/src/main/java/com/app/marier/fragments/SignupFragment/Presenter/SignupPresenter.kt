package com.app.marier.fragments.SignupFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.marier.Handler.CreateUserHandler
import com.app.marier.Models.RegisterUser.CreateUserExample
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.fragments.SignupFragment.SignupFragment
import com.app.marier.fragments.SignupFragment.View.SignupView

class SignupPresenter(
    private val activity: FragmentActivity,
    private val signupView: SignupView,
   private val signupFragment: SignupFragment
) {


    fun createaccount(name:String,gender:String,dob:String,phonenum:String) {
        val phone :Int = phonenum.toInt()
        if (Utils.isNetworkConnected(activity!!)) {
            signupView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.createMethod(name, "test@gmail.com" ,phone,gender,dob,"har","Point"  , object :
                CreateUserHandler {
                override fun onSuccess(createuserexample: CreateUserExample?) {
                    if (createuserexample!=null){
                        signupView.hideDialog()
                        NavHostFragment.findNavController(signupFragment).navigate(R.id.action_signupFragment_to_enterotpFragment)
                        signupView.showMessage(activity,createuserexample?.message)
                        CSPreferences.putString(activity,Utils.TOKEN,createuserexample.data.token)

                    }else{
                        signupView.hideDialog()
                        signupView.showMessage(activity,createuserexample?.message)

                    }




                }

                override fun onError(message: String?) {
                    signupView.hideDialog()
                    signupView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

}