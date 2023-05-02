package com.app.marier.fragments.SignupFragment

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.databinding.FragmentSignupBinding
import com.app.marier.fragments.SignupFragment.Presenter.SignupPresenter
import com.app.marier.fragments.SignupFragment.View.SignupView
import java.util.*


class SignupFragment : Fragment(), View.OnClickListener,SignupView {
    private var binding:FragmentSignupBinding?=null
    private var signupPresenter:SignupPresenter?=null
    private     var  selected:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(layoutInflater,container,false)
        val view:View = binding?.root!!
        signupPresenter = SignupPresenter(requireActivity(),this,this)
        listeners()
        spinner()

        return view
    }
    private fun listeners(){
        binding!!.tvdateselect.setOnClickListener(this)
        binding!!.tvsignup.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvdateselect->{
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    // on below line we are passing context.
                    activity as FragmentActivity,
                    { view, year, monthOfYear, dayOfMonth ->
                        binding?.tvdateselect?.text =(year.toString()+"-"+(monthOfYear+1)+"-"+dayOfMonth.toString())
//                            (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()
            }
            R.id.tvsignup->{
                signupPresenter?.createaccount(binding?.etfullname?.text.toString(),selected.toString(),binding?.tvdateselect?.text.toString(),
                binding?.etphonennum?.text.toString())

//                findNavController().navigate(R.id.action_signupFragment_to_enterotpFragment)
            }

        }
    }

    private fun spinner(){
        val languages = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(activity as FragmentActivity,
            R.layout.simple_spinner_item,R.id.tvspinner, languages)
        binding!!.spinner.adapter = adapter
        binding!!.spinner.setPrompt("Select your favorite Planet!");
        binding!!.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
            selected = binding!!.spinner.getSelectedItem().toString();
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
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