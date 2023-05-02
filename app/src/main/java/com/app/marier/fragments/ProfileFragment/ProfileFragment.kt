package com.app.marier.fragments.ProfileFragment

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.marier.Models.GetCurrentUser.Data
import com.app.marier.R
import com.app.marier.Utils.CSPreferences
import com.app.marier.Utils.Utils
import com.app.marier.`interface`.ProfileCurrentUser
import com.app.marier.activities.CurrentLocationActivity.CurrentLocationActivity
import com.app.marier.activities.EditProfileActivity.EditProfileActivity
import com.app.marier.activities.HomeActivity.HomeActivity
import com.app.marier.activities.InviteFriendScreenActivity.InviteFriendScreenActivity
import com.app.marier.activities.NotificationActivity.NotificationActivity
import com.app.marier.activities.OnBoardSplashActivity.OnBoardSplashActivity
import com.app.marier.activities.PlanSettingActivity.PlanSettingActivity
import com.app.marier.activities.RecentPassesActivity.RecentPassesActivity
import com.app.marier.activities.RecommendationActivity.RecommendationActivity
import com.app.marier.activities.RequestHelpActivity.RequestHelpActivity
import com.app.marier.databinding.FragmentProfileBinding
import com.app.marier.fragments.FavouriteFragment.FavouriteFragment
import com.app.marier.fragments.ProfileFragment.Presenter.ProfilePresenter
import com.app.marier.fragments.ProfileFragment.View.ProfileView
import com.bumptech.glide.Glide
import java.io.IOException


class ProfileFragment : Fragment(), View.OnClickListener,ProfileView,ProfileCurrentUser {
    private var binding: FragmentProfileBinding? = null
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var bitmap: Bitmap? = null
    private var profilePresenter:ProfilePresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        val view: View = binding!!.root
        HomeActivity?.bottomNavigationView?.visibility = View.VISIBLE

        listeners()
        profilePresenter = ProfilePresenter(requireActivity(),this,this)
        profilePresenter?.getcurrentuser()
        return view
    }

    private fun listeners() {
        binding?.tvprofileedit?.setOnClickListener(this)
        binding?.imgselectimage?.setOnClickListener(this)
        binding?.tvplansetting?.setOnClickListener(this)
        binding?.linlogout?.setOnClickListener(this)
        binding?.tvmycurrentlocation?.setOnClickListener(this)
        binding?.linnotification?.setOnClickListener(this)
        binding?.linhelp?.setOnClickListener(this)
        binding?.linrecentpasses?.setOnClickListener(this)
        binding?.linplansetting?.setOnClickListener(this)
        binding?.linshowme?.setOnClickListener(this)
        binding?.linrecommendation?.setOnClickListener(this)
        binding?.lininvitefriend?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tvprofileedit -> {
                var intent = Intent(activity, EditProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.imgselectimage -> {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }
            R.id.linplansetting -> {
                var intent = Intent(activity, PlanSettingActivity::class.java)
                startActivity(intent)
            }
            R.id.linlogout->{
                showDialog()
            }
            R.id.tvmycurrentlocation->{
                var intent = Intent(activity,CurrentLocationActivity::class.java)
                startActivity(intent)
            }
            R.id.linnotification->{
                var intent = Intent(activity,NotificationActivity::class.java)
                startActivity(intent)
            }
            R.id.linhelp->{
                var intent = Intent(activity,RequestHelpActivity::class.java)
                startActivity(intent)
            }
            R.id.linrecentpasses->{
                var intent = Intent(activity,RecentPassesActivity::class.java)
                startActivity(intent)
            }
            R.id.linshowme->{
                showmedialog()
            }
            R.id.linrecommendation->{
                var intent = Intent(requireActivity(),RecommendationActivity::class.java)
                startActivity(intent)
            }
            R.id.lininvitefriend->{
                var intent = Intent(activity as FragmentActivity,InviteFriendScreenActivity::class.java)
                startActivity(intent)
//                HomeActivity?.bottomNavigationView?.visibility = View.GONE
//                findNavController().navigate(R.id.action_profileFragment_to_favouriteFragment)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
//            img_profile?.setImageURI(imageUri)
            try {
                bitmap =
                    MediaStore.Images.Media.getBitmap(activity!!.contentResolver, imageUri)
                profilePresenter?.uploadprofileimage(bitmap!!)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            binding!!.imgProfile?.setImageURI(imageUri)
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

    override fun getData(activity: FragmentActivity, data: Data) {
        binding?.tvusername?.text =data.name
        binding?.tvname?.text =data.name
        binding?.tvphnnum?.text =data.phoneNumber.toString()
        binding?.tvdob?.text =   data.dob.toString()
        Glide.with(activity)
            .load(data.avatar).placeholder(R.drawable.rock)
            .into(binding?.imgProfile!!)

    }
    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.logoutdialog)
        val window = dialog.window
        val wlp = window!!.attributes
        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val btn_signout = dialog.findViewById<View>(R.id.btn_signout) as Button
//        val gender: String = CSPreferences.readString(activity, Utils.GENDERSELECT)

        btn_signout.setOnClickListener {
            CSPreferences.putString(requireActivity()!!, Utils.USERLOGIN, "false")
            val intent1 = Intent(activity, OnBoardSplashActivity::class.java)
            startActivity(intent1)
            activity?.finish()

        }
        val btn_cancel = dialog.findViewById<View>(R.id.btn_cancel) as Button
        btn_cancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun showmedialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.showmedialog)
        val window = dialog.window
        val wlp = window!!.attributes
        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window.attributes = wlp
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val tvmen = dialog.findViewById<View>(R.id.tvmen) as TextView
        val tvwomen = dialog.findViewById<View>(R.id.tvwomen) as TextView
        val tvother = dialog.findViewById<View>(R.id.tvother) as TextView
        tvmen.setOnClickListener{
            binding!!.tvshowmeselected.text = tvmen.text
            dialog?.dismiss()
        }
        tvwomen.setOnClickListener{
            binding!!.tvshowmeselected.text =tvwomen.text
            dialog?.dismiss()
        }
        tvother?.setOnClickListener{
            binding!!.tvshowmeselected.text =tvother.text
            dialog?.dismiss()
        }
        dialog.show()

    }



}