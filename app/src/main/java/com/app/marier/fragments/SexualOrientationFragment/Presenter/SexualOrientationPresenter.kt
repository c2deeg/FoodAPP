package com.app.marier.fragments.SexualOrientationFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Handler.AddSexualOrientationHandler
import com.app.marier.Handler.GetSexualOrientationHandler
import com.app.marier.Models.AddsexualOrientation.AddSexualOrientationExample
import com.app.marier.Models.GetSexualOrientation.Data
import com.app.marier.Models.GetSexualOrientation.GetSexualOrientationExample
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.`interface`.sexualorientationclicklistner
import com.app.marier.adapter.SexualorientationRecylerAdapter
import com.app.marier.fragments.SexualOrientationFragment.SexualOrientationFragment
import com.app.marier.fragments.SexualOrientationFragment.View.SexualOrientationView

class SexualOrientationPresenter(
    private val activity: FragmentActivity,
    private val sexualOrientationFragment: SexualOrientationFragment,
    private val sexualOrientationView: SexualOrientationView,
    private val sexualOrientationRecylerview: RecyclerView?,
    private val clicklistener: sexualorientationclicklistner
) {
    private var sexualorientationRecylerAdapter:SexualorientationRecylerAdapter?=null

    fun getsexualorientation() {
        if (Utils.isNetworkConnected(activity!!)) {
            sexualOrientationView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getsexualorientation( object :
                GetSexualOrientationHandler {
                override fun onSuccess(getSexualOrientationExample: GetSexualOrientationExample?) {
                    if (getSexualOrientationExample!=null){
                        sexualOrientationView.hideDialog()
//                        NavHostFragment.findNavController(sexualOrientationFragment).navigate(R.id.action_myPhoneNumberFragment_to_enterotpFragment)
                        sexualOrientationView.showMessage(activity,getSexualOrientationExample?.message)
                        initRecyclerview(getSexualOrientationExample.data)
                    }else{
                        sexualOrientationView.hideDialog()
                        sexualOrientationView.showMessage(activity,getSexualOrientationExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    sexualOrientationView.hideDialog()
                    sexualOrientationView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
    private fun initRecyclerview(data: List<Data>) {
        sexualOrientationView.hideDialog()
        sexualorientationRecylerAdapter = SexualorientationRecylerAdapter(activity,data,clicklistener)
       sexualOrientationRecylerview?.layoutManager  = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
      sexualOrientationRecylerview?.adapter = sexualorientationRecylerAdapter
    }

    fun addsexualorientation(sexualorientation:ArrayList<String>) {
        var token = CSPreferences.readString(activity,Utils.TOKEN)
        var userid = CSPreferences.readString(activity,Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            sexualOrientationView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.addsexualorientation( token!!,userid!!,sexualorientation, object :
                AddSexualOrientationHandler {
                override fun onSuccess(addsexualOrientationExample: AddSexualOrientationExample?) {
                    if (addsexualOrientationExample!=null){
                        sexualOrientationView.hideDialog()
                        NavHostFragment.findNavController(sexualOrientationFragment).navigate(R.id.action_sexualOrientationFragment_to_addPhotosFragment)
                        sexualOrientationView.showMessage(activity,addsexualOrientationExample?.data)
                    }else{
                        sexualOrientationView.hideDialog()
                        sexualOrientationView.showMessage(activity,addsexualOrientationExample?.data)
                    }
                }

                override fun onError(message: String?) {
                    sexualOrientationView.hideDialog()
                    sexualOrientationView.showMessage(activity,message)
                }


            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}