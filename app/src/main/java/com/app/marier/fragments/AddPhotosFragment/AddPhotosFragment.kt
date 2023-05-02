package com.app.marier.fragments.AddPhotosFragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.`interface`.PickiImagesAdapterInterface
import com.app.marier.activities.HomeActivity.HomeActivity
import com.app.marier.adapter.AddphotosRecyclerAdapter
import com.app.marier.databinding.FragmentAddPhotosBinding
import com.app.marier.fragments.AddPhotosFragment.Presenter.AddPhotosPresenter
import com.app.marier.fragments.AddPhotosFragment.View.AddPhotosView

class AddPhotosFragment : Fragment(), PickiImagesAdapterInterface, View.OnClickListener,AddPhotosView {
    private var binding: FragmentAddPhotosBinding? = null
    private var addphotosRecyclerAdapter: AddphotosRecyclerAdapter? = null
    var uri: ArrayList<Uri> = ArrayList()
    private val pickImage = 100
    private var addPhotosPresenter:AddPhotosPresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddPhotosBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        initRecyler()
        listeners()
        addPhotosPresenter = AddPhotosPresenter(requireActivity(),this,this)


        return view
    }

    private fun initRecyler() {
        addphotosRecyclerAdapter = AddphotosRecyclerAdapter(activity as FragmentActivity, this, uri)
        binding!!.addphotosrecylerview.layoutManager = GridLayoutManager(activity, 2)
        binding!!.addphotosrecylerview.adapter = addphotosRecyclerAdapter

    }

    private fun listeners(){
        binding!!.tvcontinue.setOnClickListener(this)
    }

    override fun picimages(position: Int) {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        gallery.type = "image/*"
        gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(gallery, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            if (data?.clipData != null) {
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    uri.add(data.clipData!!.getItemAt(i).uri)
                }
                Toast.makeText(activity, uri.size.toString(), Toast.LENGTH_SHORT).show()
                initRecyler()
                addphotosRecyclerAdapter?.notifyDataSetChanged()
                addPhotosPresenter?.addgalleryimages(uri)
            } else {
                Toast.makeText(activity, "uri.size", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvcontinue->{
              findNavController().navigate(R.id.action_addPhotosFragment_to_interestFragment)
            }

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


}