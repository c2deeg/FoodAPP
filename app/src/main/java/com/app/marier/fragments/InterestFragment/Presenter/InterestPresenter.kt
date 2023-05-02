package com.app.marier.fragments.InterestFragment.Presenter

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.marier.Handler.AddinterestHandler
import com.app.marier.Handler.GetAllInterestHandler
import com.app.marier.Models.AddinterestExample.AddinterestExample
import com.app.marier.Models.GetAllInterest.GetallInterestExample
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.Utils.WebServices
import com.app.marier.`interface`.InterestRecyclerClicklistner
import com.app.marier.activities.HomeActivity.HomeActivity
import com.app.marier.adapter.ChipRecyclerAdapter
import com.app.marier.fragments.InterestFragment.InterestFragment
import com.app.marier.fragments.InterestFragment.View.InterestView

class InterestPresenter(
    private val activity: FragmentActivity,
    private val interestFragment: InterestFragment,
    private val interestView: InterestView,
   private val interserrccyler: RecyclerView,
   private val clicklistner: InterestRecyclerClicklistner
) {
    private var chipRecyclerAdapter: ChipRecyclerAdapter? = null

    fun getinterest() {
        var token =CSPreferences.readString(activity,Utils.TOKEN)

        if (Utils.isNetworkConnected(activity!!)) {
            interestView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getallinterest(token!!, object :
                GetAllInterestHandler {
                override fun onSuccess(getallInterestExample: GetallInterestExample?) {
                    if (getallInterestExample!=null){
                        interestView.hideDialog()
                        interestView.showMessage(activity,getallInterestExample?.message)

                        chipRecyclerAdapter = ChipRecyclerAdapter(activity as FragmentActivity,getallInterestExample.data,clicklistner)
                        val layoutManager = GridLayoutManager(activity, 6)


                        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                when (position) {
                                    0, 1, 2 -> return 2
                                    3, 4 -> return 3
                                    5 -> return 3
                                    10->return 2
                                    else -> return 2
                                }


                            }

                        }
                       interserrccyler.layoutManager = layoutManager


                   interserrccyler.adapter = chipRecyclerAdapter



                    }else{
                        interestView.hideDialog()
                        interestView.showMessage(activity,getallInterestExample?.message)

                    }
                }

                override fun onError(message: String?) {
                    interestView.hideDialog()
                    interestView.showMessage(activity,message)

                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    fun addinterest(interest:ArrayList<String>) {
        var token =CSPreferences.readString(activity,Utils.TOKEN)
        var userid =CSPreferences.readString(activity,Utils.USERID)

        if (Utils.isNetworkConnected(activity!!)) {
            interestView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.addinterest(token!!,userid!!,interest, object :
                AddinterestHandler {
                override fun onSuccess(addinterestExample: AddinterestExample?) {

                    if (addinterestExample!=null){
                        interestView.hideDialog()
                        interestView.showMessage(activity,addinterestExample?.data)
                        CSPreferences.putString(activity as FragmentActivity,Utils.USERLOGIN,"true")
                        var intent = Intent(activity, HomeActivity::class.java)
                       activity?.startActivity(intent)
                    }else{
                        interestView.hideDialog()
                        interestView.showMessage(activity,addinterestExample?.data)

                    }

                }

                override fun onError(message: String?) {
                    interestView.hideDialog()
                    interestView.showMessage(activity,message)
                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}