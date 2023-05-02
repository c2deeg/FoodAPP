package com.app.marier.activities.EditProfileActivity.Presenter

import android.widget.Toast
import com.app.marier.Handler.GetCurrentUserHandler
import com.app.marier.Handler.UpdateUserDetailHandler
import com.app.marier.Models.EditUserDetail.EditUserDetailExample
import com.app.marier.Models.GetCurrentUser.GetCurrentUserExample
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.activities.EditProfileActivity.EditProfileActivity
import com.app.marier.activities.EditProfileActivity.View.EditProfileView

class EditProfilePresenter(
   private val activity: EditProfileActivity,
   private val editProfileView: EditProfileView
) {

    fun getcurrentuser() {
        var token = CSPreferences.readString(activity, Utils.TOKEN)
        var userid = CSPreferences.readString(activity, Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            editProfileView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getcurrentuser(token!!,userid!!, object :
                GetCurrentUserHandler {
                override fun onSuccess(getCurrentUserExample: GetCurrentUserExample?) {
                    if (getCurrentUserExample!=null){
                        editProfileView.hideDialog()
                        editProfileView.showMessage(activity,getCurrentUserExample?.message)
                        editProfileView.getdata(activity,getCurrentUserExample?.data)
                    }else{
                        editProfileView.hideDialog()
                        editProfileView.showMessage(activity,getCurrentUserExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    editProfileView.hideDialog()
                    editProfileView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
    //updateuserdetail
    fun updateuserdetail(
        etname: String,
        tvdate: String,
        tvlocation: String,
        tvlangauge: String,
        minValue: String,
        maxValue: String,
        tvmaxdistance: String
    ) {
        var token = CSPreferences.readString(activity, Utils.TOKEN)
        var userid = CSPreferences.readString(activity, Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            editProfileView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.updatecurrentuser(token!!,userid!!, etname,CSPreferences.readString(activity,Utils.PHONENUM).toString(),"",
                tvdate,tvlocation,minValue.toInt(),maxValue.toInt(),100,"",tvlangauge, object :
                UpdateUserDetailHandler {
                override fun onSuccess(editUserDetailExample: EditUserDetailExample?) {
                    if (editUserDetailExample!=null){
                       activity.finish()
//                        editProfileView.hideDialog()
                        editProfileView.showMessage(activity,editUserDetailExample?.message)
                    }else{
                        editProfileView.hideDialog()
                        editProfileView.showMessage(activity,editUserDetailExample?.message)
                    }
                }



                override fun onError(message: String?) {
                    editProfileView.hideDialog()
                    editProfileView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}