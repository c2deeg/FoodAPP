package com.app.marier.fragments.LikeFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.marier.Utils.Utils
import com.app.marier.adapter.LikeRecyclerAdapter
import com.app.marier.databinding.FragmentLikeBinding
import com.app.marier.fragments.LikeFragment.Presenter.LikePresenter
import com.app.marier.fragments.LikeFragment.View.LikeView

class LikeFragment : Fragment(),LikeView {
    private var binding:FragmentLikeBinding?=null
    private var likePresenter:LikePresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLikeBinding.inflate(layoutInflater, container, false)
        val view: View = binding!!.root
        initRecyclerview()
        likePresenter = LikePresenter(requireActivity(),this, binding?.likerecyclerview)
        likePresenter?.getlikes()

        return view
    }

    private fun initRecyclerview(){

    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity,msg)

    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity,activity?.fragmentManager)

    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

}