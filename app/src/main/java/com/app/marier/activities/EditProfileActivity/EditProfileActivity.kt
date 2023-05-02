package com.app.marier.activities.EditProfileActivity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.app.marier.Models.GetCurrentUser.Data
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.activities.EditProfileActivity.Presenter.EditProfilePresenter
import com.app.marier.activities.EditProfileActivity.View.EditProfileView
import com.app.marier.databinding.ActivityEditProfileBinding
import java.util.*

class EditProfileActivity : AppCompatActivity(), View.OnClickListener,EditProfileView {
    private var binding:ActivityEditProfileBinding?=null
    private var editProfilePresenter:EditProfilePresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        listeners()
        editProfilePresenter = EditProfilePresenter(this,this)
        editProfilePresenter?.getcurrentuser()
    }
    private fun listeners(){
        binding!!.tvsave.setOnClickListener(this)
        binding!!.tvdate.setOnClickListener(this)
        binding!!.imgback.setOnClickListener(this)

    }

    private fun datepicker(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            // on below line we are passing context.
            this,
            { view, year, monthOfYear, dayOfMonth ->
                binding?.tvdate?.text =
                    (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }



    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvsave->{
                finish()
//                editProfilePresenter?.updateuserdetail(binding?.etname?.text.toString(),binding?.tvdate?.text.toString(),
//
//                )

            }
            R.id.tvdate->datepicker()
            R.id.imgback->finish()

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

    override fun getdata(activity: EditProfileActivity, data: Data?) {
        binding?.etname?.setText(data?.name)
        binding?.tvdate?.setText(data?.dob)

    }

}