package com.app.marier.fragments.HomeFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.marier.Handler.GetRandomUserHandler
import com.app.marier.Handler.LikeUserHandler
import com.app.marier.Models.GetRandomList.GetRandomUserExample
import com.app.marier.Models.LikeUser.LikeUserExample
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.`interface`.HomepresenterData
import com.app.marier.adapter.CardStackAdapter
import com.app.marier.fragments.HomeFragment.HomeFragment
import com.app.marier.fragments.HomeFragment.View.HomeView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackView

class HomePresenter(
    private val activity: FragmentActivity,
    private val homeFragment: HomeFragment,
    private val homeView: HomeView,
    private val cardStackView: CardStackView?,
   private val homepresenterData: HomepresenterData
) {
    private var adapter: CardStackAdapter?=null
    private var manager: CardStackLayoutManager?=null


    fun getrandomuser() {
        if (Utils.isNetworkConnected(activity!!)) {
            var token = CSPreferences.readString(activity,Utils.TOKEN)
            var id  =CSPreferences.readString(activity,Utils.USERID)
            homeView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getrandomUser(token!!,id!!, object :
                GetRandomUserHandler {
                override fun onSuccess(getRandomUserExample: GetRandomUserExample?) {
                    if (getRandomUserExample!=null){
                        homeView.hideDialog()
                        homeView.showMessage(activity,getRandomUserExample?.message)
                        homepresenterData.getdata(activity,getRandomUserExample.data)
                    }else{
                        homeView.hideDialog()
                        homeView.showMessage(activity,getRandomUserExample?.message)
                    }
                    }



                override fun onError(message: String?) {
                    homeView.hideDialog()
                    homeView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    fun likeuser(userid:String,superlike:String) {
        var selfuserid = CSPreferences.readString(activity, Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
//            homeView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.likeuser(selfuserid!!,userid!!,superlike, object :
                LikeUserHandler {
                override fun onSuccess(likeUserExample: LikeUserExample?) {
                    if (likeUserExample!=null){
                        homeView.hideDialog()
                        homeView.showMessage(activity,likeUserExample?.message)
                    }else{
                        homeView.hideDialog()
                        homeView.showMessage(activity,likeUserExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    homeView.hideDialog()
                    homeView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}