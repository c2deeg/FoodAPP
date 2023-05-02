package com.app.marier.fragments.InterestFragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.`interface`.InterestRecyclerClicklistner
import com.app.marier.adapter.ChipRecyclerAdapter
import com.app.marier.databinding.FragmentInterestBinding
import com.app.marier.fragments.InterestFragment.Presenter.InterestPresenter
import com.app.marier.fragments.InterestFragment.View.InterestView
import kotlin.collections.ArrayList


class InterestFragment : Fragment(), View.OnClickListener,InterestView,InterestRecyclerClicklistner {
    private var arraylist: ArrayList<String> = ArrayList()
    private var binding: FragmentInterestBinding? = null
    private var chipRecyclerAdapter: ChipRecyclerAdapter? = null
    var value: Int = 2
    private var interestPresenter:InterestPresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInterestBinding.inflate(layoutInflater, container, false)
        val view: View = binding!!.root
        listeners()
        interestPresenter = InterestPresenter(requireActivity(),this,this,binding!!.interserrccyler,this)
        interestPresenter?.getinterest()



        return view
    }

    private fun listeners() {
        binding!!.tvcontinue.setOnClickListener(this)
        binding!!.imgback.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvcontinue->{
                interestPresenter?.addinterest(arraylist)

            }
            R.id.imgback->findNavController().navigateUp()
        }
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

    override fun passdata(arrayList: ArrayList<String>) {
        this.arraylist = arrayList
    }


}