package com.app.marier.activities.UserDetailActivity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.app.marier.Models.GetCurrentUser.Data
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.activities.UserDetailActivity.Presenter.UserDetailPresenter
import com.app.marier.activities.UserDetailActivity.View.UserDetailView
import com.app.marier.adapter.GalleryRecylerAdapter
import com.app.marier.adapter.UserDetailInterestRecylerAdapter
import com.app.marier.adapter.UserorientationRecylerAdapter
import com.app.marier.databinding.ActivityUserDetailBinding
import com.bumptech.glide.Glide

class UserDetailActivity : AppCompatActivity(), UserDetailView, View.OnClickListener {
    private var binding: ActivityUserDetailBinding? = null
    private var userDetailInterestRecylerAdapter: UserDetailInterestRecylerAdapter? = null
    private var userorientationRecylerAdapter: UserorientationRecylerAdapter? = null
    private var galleryRecylerAdapter: GalleryRecylerAdapter? = null
    private var userDetailPresenter: UserDetailPresenter? = null
    private var userid: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        listeners()
        userid = intent.getStringExtra("userid")
        userDetailPresenter = UserDetailPresenter(this, this)
        userDetailPresenter?.getcurrentuser(userid!!)
    }
    private fun listeners(){
        binding?.linlike?.setOnClickListener(this)
        binding?.linsuperlike?.setOnClickListener(this)
        binding?.linexit?.setOnClickListener(this)
    }




    override fun getData(activity: UserDetailActivity, data: Data) {
        binding?.tvname?.text = data.name
        binding?.tvlocation?.text = data.loc

        Glide.with(activity)
            .load(data.avatar).placeholder(R.drawable.userplaceholder)
            .into(binding?.imguser!!)
        //interestrecyclerview
        userDetailInterestRecylerAdapter = UserDetailInterestRecylerAdapter(this, data.interests)
        binding?.interesthorizontalrecylerview?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.interesthorizontalrecylerview?.adapter = userDetailInterestRecylerAdapter
        //userorientationrecyclerview
        userorientationRecylerAdapter = UserorientationRecylerAdapter(this,data.sexualOrientations)
        binding?.orientationrecyclerview?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding?.orientationrecyclerview?.adapter =userorientationRecylerAdapter
        //gallery recyclerview
        galleryRecylerAdapter = GalleryRecylerAdapter(this,data.gallery)
        binding?.galleryrecyclerview?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.galleryrecyclerview?.adapter = galleryRecylerAdapter
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding?.galleryrecyclerview)
        binding?.indicator?.attachToRecyclerView(binding?.galleryrecyclerview!!, pagerSnapHelper);

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.linlike->userDetailPresenter?.likeuser(userid!!,"false")
            R.id.linsuperlike->userDetailPresenter?.likeuser(userid!!,"true")
            R.id.linexit->finish()

        }
    }
    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, activity?.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

}