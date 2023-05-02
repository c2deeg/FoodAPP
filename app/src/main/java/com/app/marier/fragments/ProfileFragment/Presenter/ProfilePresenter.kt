package com.app.marier.fragments.ProfileFragment.Presenter

import android.graphics.Bitmap
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.marier.Handler.GetCurrentUserHandler
import com.app.marier.Handler.UploadProfileImageHandler
import com.app.marier.Models.GetCurrentUser.GetCurrentUserExample
import com.app.marier.Models.UploadProfileImage.UploadProfileImageExample
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.`interface`.ProfileCurrentUser
import com.app.marier.fragments.ProfileFragment.View.ProfileView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.util.*

class ProfilePresenter(
    private val activity: FragmentActivity,
    private val profileView: ProfileView,
   private val profileCurrentUser: ProfileCurrentUser
) {

    fun getcurrentuser() {
        var token = CSPreferences.readString(activity,Utils.TOKEN)
        var userid = CSPreferences.readString(activity,Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            profileView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getcurrentuser(token!!,userid!!, object :
                GetCurrentUserHandler {
                override fun onSuccess(getCurrentUserExample: GetCurrentUserExample?) {
                    if (getCurrentUserExample!=null){
                        CSPreferences.putString(activity,Utils.Sex,getCurrentUserExample.data.sex)
                        CSPreferences.putString(activity,Utils.PHONENUM,getCurrentUserExample.data.phoneNumber.toString())
                        profileCurrentUser.getData(activity,getCurrentUserExample.data)
                        profileView.hideDialog()
                        profileView.showMessage(activity,getCurrentUserExample?.message)
                    }else{
                        profileView.hideDialog()
                        profileView.showMessage(activity,getCurrentUserExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    profileView.hideDialog()
                    profileView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
    //uploadprofileuimage
    fun uploadprofileimage(photo: Bitmap) {
         var imagePart: MultipartBody.Part? = null

        if (photo != null) {
            val stream = ByteArrayOutputStream()
            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream)
            val photoByteArray = stream.toByteArray()
            val requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), photoByteArray)

            val random = Random()
            imagePart = MultipartBody.Part.createFormData(
                "image",
                "abc" + random.nextInt(1000000) + ".jpg",
                requestFile
            )
        }
        var userid = CSPreferences.readString(activity,Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            profileView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.uploadprofileimage(imagePart!!,userid!!, object :
                UploadProfileImageHandler {
                override fun onSuccess(uploadProfileImageExample: UploadProfileImageExample?) {
                    if (uploadProfileImageExample!=null){
                        profileView.hideDialog()
                        profileView.showMessage(activity,uploadProfileImageExample?.data)
                    }else{
                        profileView.hideDialog()
                        profileView.showMessage(activity,uploadProfileImageExample?.data)
                    }
                }

                override fun onError(message: String?) {
                    profileView.hideDialog()
                    profileView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}