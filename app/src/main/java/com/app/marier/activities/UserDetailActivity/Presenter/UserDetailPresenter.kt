package com.app.marier.activities.UserDetailActivity.Presenter

import android.widget.Toast
import com.app.marier.Handler.GetCurrentUserHandler
import com.app.marier.Handler.LikeUserHandler
import com.app.marier.Models.GetCurrentUser.GetCurrentUserExample
import com.app.marier.Models.LikeUser.LikeUserExample
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.activities.UserDetailActivity.UserDetailActivity
import com.app.marier.activities.UserDetailActivity.View.UserDetailView

class UserDetailPresenter(
   private val activity: UserDetailActivity,
    private val userDetailView: UserDetailView
) {
    fun getcurrentuser(userid:String) {
        var token = CSPreferences.readString(activity, Utils.TOKEN)
        if (Utils.isNetworkConnected(activity!!)) {
            userDetailView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getcurrentuser(token!!,userid!!, object :
                GetCurrentUserHandler {
                override fun onSuccess(getCurrentUserExample: GetCurrentUserExample?) {
                    if (getCurrentUserExample!=null){
                        CSPreferences.putString(activity, Utils.Sex,getCurrentUserExample.data.sex)
                        CSPreferences.putString(activity, Utils.PHONENUM,getCurrentUserExample.data.phoneNumber.toString())
                        userDetailView.getData(activity,getCurrentUserExample.data)
                        userDetailView.hideDialog()
                        userDetailView.showMessage(activity,getCurrentUserExample?.message)
                    }else{
                        userDetailView.hideDialog()
                        userDetailView.showMessage(activity,getCurrentUserExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    userDetailView.hideDialog()
                    userDetailView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
    //likeuser
    fun likeuser(userid:String,superlike:String) {
        var selfuserid = CSPreferences.readString(activity, Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            userDetailView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.likeuser(selfuserid!!,userid!!,superlike, object :
                LikeUserHandler {
                override fun onSuccess(likeUserExample: LikeUserExample?) {
                    if (likeUserExample!=null){
                        userDetailView.hideDialog()
                        userDetailView.showMessage(activity,likeUserExample?.message)
                    }else{
                        userDetailView.hideDialog()
                        userDetailView.showMessage(activity,likeUserExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    userDetailView.hideDialog()
                    userDetailView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}