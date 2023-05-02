package com.app.marier.fragments.LikeFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Handler.GetLikeHandler
import com.app.marier.Models.GetLikesModel.GetLikeExample
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.adapter.LikeRecyclerAdapter
import com.app.marier.fragments.LikeFragment.View.LikeView

class LikePresenter(
    private val activity: FragmentActivity,
    private val likeView: LikeView,
    private val likerecyclerview: RecyclerView?
) {


    fun getlikes() {
        if (Utils.isNetworkConnected(activity!!)) {
            var token = CSPreferences.readString(activity, Utils.TOKEN)
            var id  = CSPreferences.readString(activity, Utils.USERID)
            likeView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getlikeMethod(token!!,id!!, object :
                GetLikeHandler {
                override fun onSuccess(getLikeExample: GetLikeExample?) {
                    if (getLikeExample!=null){
                        likeView.hideDialog()
                        likeView.showMessage(activity,getLikeExample?.message)
                        var likeRecyclerAdapter = LikeRecyclerAdapter(activity ,getLikeExample.data)
                        likerecyclerview?.layoutManager =  GridLayoutManager(activity, 2)
                       likerecyclerview?.adapter = likeRecyclerAdapter
                    }else{
                        likeView.hideDialog()
                        likeView.showMessage(activity,getLikeExample?.message)
                    }
                }



                override fun onError(message: String?) {
                    likeView.hideDialog()
                    likeView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
}