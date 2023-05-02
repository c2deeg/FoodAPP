package com.app.marier.fragments.FavouriteFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.marier.R
import com.app.marier.adapter.FriendsRecyclerAdapter
import com.app.marier.adapter.RequetsFavScreenRecyclerAdapter
import com.app.marier.databinding.FragmentFavouriteBinding
import com.app.marier.fragments.FavouriteFragment.Presenter.FavouritePresenter
import com.app.marier.fragments.FavouriteFragment.View.FavouriteView

class FavouriteFragment : Fragment(), View.OnClickListener,FavouriteView {
    private var binding: FragmentFavouriteBinding? = null
    private var friendsrecycleradapter: FriendsRecyclerAdapter? = null
    private var searchFavScreenAdapter: RequetsFavScreenRecyclerAdapter? = null
    private var favouritePresenter:FavouritePresenter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        val view: View = binding!!.root
        listeners()
        initReyclerview()
        favouritePresenter= FavouritePresenter(requireActivity(),this)



        return view
    }

    private fun initReyclerview() {
        searchFavScreenAdapter = RequetsFavScreenRecyclerAdapter(activity as FragmentActivity)
        binding?.requestrecyclerview?.layoutManager =
            LinearLayoutManager(activity as FragmentActivity, LinearLayoutManager.VERTICAL,false)
        binding?.requestrecyclerview?.adapter = searchFavScreenAdapter

        //nearbyrecylerview
        friendsrecycleradapter = FriendsRecyclerAdapter(activity as FragmentActivity)
        binding?.friendsrecyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding?.friendsrecyclerview?.adapter = friendsrecycleradapter
    }

    private fun listeners() {
        binding?.tvrequest?.setOnClickListener(this)
        binding?.tvfriends?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tvrequest -> {
                binding!!.requestrecyclerview.visibility = View.VISIBLE
                binding!!.friendsrecyclerview.visibility = View.GONE
                binding!!.tvfriends.setTextColor(resources.getColor(R.color.purple_200))
                binding!!.tvrequest.setTextColor(resources.getColor(R.color.appcolor    ))
            }
            R.id.tvfriends -> {
                binding!!.requestrecyclerview.visibility = View.GONE
                binding!!.friendsrecyclerview.visibility = View.VISIBLE
                binding!!.tvfriends.setTextColor(resources.getColor(R.color.appcolor))
                binding!!.tvrequest.setTextColor(resources.getColor(R.color.purple_200))

            }
        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {

    }

    override fun showDialog(activity: Activity?) {
    }

    override fun hideDialog() {
    }


}