package com.app.marier.fragments.FavouriteFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.marier.Handler.GetLikeHandler
import com.app.marier.Handler.GetRandomUserHandler
import com.app.marier.Models.GetLikesModel.GetLikeExample
import com.app.marier.Models.GetRandomList.GetRandomUserExample
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.fragments.FavouriteFragment.FavouriteFragment
import com.app.marier.fragments.FavouriteFragment.View.FavouriteView

class FavouritePresenter(private val activity: FragmentActivity, private val favouriteView: FavouriteView) {


    fun getlikes() {
        if (Utils.isNetworkConnected(activity!!)) {
            var token = CSPreferences.readString(activity, Utils.TOKEN)
            var id  = CSPreferences.readString(activity, Utils.USERID)
            favouriteView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getlikeMethod(token!!,id!!, object :
                GetLikeHandler {
                override fun onSuccess(getLikeExample: GetLikeExample?) {
                    if (getLikeExample!=null){
                        favouriteView.hideDialog()
                        favouriteView.showMessage(activity,getLikeExample?.message)
                    }else{
                        favouriteView.hideDialog()
                        favouriteView.showMessage(activity,getLikeExample?.message)
                    }
                }



                override fun onError(message: String?) {
                    favouriteView.hideDialog()
                    favouriteView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

}