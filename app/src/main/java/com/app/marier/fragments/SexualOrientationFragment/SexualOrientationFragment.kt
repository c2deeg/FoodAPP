package com.app.marier.fragments.SexualOrientationFragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.marier.Utils.Utils
import com.app.marier.`interface`.sexualorientationclicklistner
import com.app.marier.adapter.SexualorientationRecylerAdapter
import com.app.marier.databinding.FragmentSexualOrientationBinding
import com.app.marier.fragments.SexualOrientationFragment.Presenter.SexualOrientationPresenter
import com.app.marier.fragments.SexualOrientationFragment.View.SexualOrientationView


class SexualOrientationFragment : Fragment(), View.OnClickListener,SexualOrientationView,
    sexualorientationclicklistner {
    private  var items: java.util.ArrayList<String> = ArrayList()
    private var item: MutableSet<String>?=null
    private var binding:FragmentSexualOrientationBinding?=null
    private var sexualorientationRecylerAdapter:SexualorientationRecylerAdapter?=null
   private var arrayList:ArrayList<String> = ArrayList()
    private var sexualOrientationPresenter:SexualOrientationPresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSexualOrientationBinding.inflate(layoutInflater,container,false)
        val view:View = binding!!.root
        listeners()
        sexualOrientationPresenter = SexualOrientationPresenter(requireActivity(),this,this, binding?.sexualOrientationRecylerview,this)
       sexualOrientationPresenter?.getsexualorientation()
        return view
    }

    private fun listeners(){
        binding!!.tvsexualorientation.setOnClickListener(this)
        binding!!.tvcontinue.setOnClickListener(this)
        binding!!.imgback.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == binding!!.tvcontinue){
            sexualOrientationPresenter?.addsexualorientation(items)
        }else if (p0==binding?.imgback){
            findNavController().navigateUp()
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

    override fun clicklisdata(item: ArrayList<String>) {
        this.items = item
        Toast.makeText(activity, item.size.toString(), Toast.LENGTH_SHORT).show()
    }


}