package com.app.marier.fragments.HomeFragment

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.app.marier.Models.GetRandomList.Data
import com.app.marier.R
import com.app.marier.Utils.Utils
import com.app.marier.`interface`.HomepresenterData
import com.app.marier.adapter.CardStackAdapter
import com.app.marier.adapter.SpotDiffCallback
import com.app.marier.databinding.FragmentHomeBinding
import com.app.marier.fragments.HomeFragment.Presenter.HomePresenter
import com.app.marier.fragments.HomeFragment.View.HomeView
import com.yuyakaido.android.cardstackview.*


class HomeFragment : Fragment(), CardStackListener, View.OnClickListener,HomeView,HomepresenterData {
    private  var data: List<Data> = ArrayList()
    private var binding:FragmentHomeBinding?=null
    private var adapter : CardStackAdapter?=null
    private var manager:CardStackLayoutManager?=null
    private var spinnerdistance:Spinner?=null
    private var tvmale:TextView?=null
    private var tvfemale:TextView?=null
    private var tvshemale:TextView?=null
    private var homePresenter:HomePresenter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view: View = binding!!.root
        manager = CardStackLayoutManager(context,this)
        homePresenter = HomePresenter(requireActivity(),this,this,binding?.cardStackView,this)
        homePresenter?.getrandomuser()
        setupCardStackView()
        listeners()
        onbackpress()


        return view
    }
    private fun listeners(){
        binding!!.linfilter.setOnClickListener(this)
        binding!!.linlike.setOnClickListener(this)
        binding!!.linexit.setOnClickListener(this)
        binding!!.linmain.setOnClickListener(this)
        binding!!.linsendmessage.setOnClickListener(this)

    }
    private fun onbackpress(){
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    private fun setupCardStackView() {
        initialize()
    }
    private fun initialize() {
        manager?.setStackFrom(StackFrom.None)
        manager?.setVisibleCount(5)
        manager?.setTranslationInterval(8.0f)
        manager?.setScaleInterval(0.95f)
        manager?.setSwipeThreshold(0.1f)
        manager?.setMaxDegree(20.0f)
        manager?.setDirections(Direction.FREEDOM)
        manager?.setCanScrollHorizontal(true)
        manager?.setCanScrollVertical(true)
        manager?.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        if (direction == Direction.Right){
            binding?.imglike?.visibility = View.VISIBLE
            binding?.linmain?.visibility = View.GONE
            binding?.imgquit?.visibility = View.GONE


        }else if (direction==Direction.Left){
            binding?.imglike?.visibility = View.GONE
            binding?.imgquit?.visibility = View.VISIBLE
            binding?.linmain?.visibility = View.GONE


        }


    }

    override fun onCardSwiped(direction: Direction?) {

        if (direction==Direction.Top){
            homePresenter?.likeuser(data.get(manager!!.topPosition-1)._id,"true")
        }else if (direction == Direction.Right){
            binding?.imglike?.visibility = View.VISIBLE
            homePresenter?.likeuser(data.get(manager!!.topPosition-1)._id,"false")
            binding?.linmain?.visibility = View.VISIBLE


        }

        if (data.size==manager?.topPosition){
            reload()
            homePresenter?.getrandomuser()
        }
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
        binding?.imglike?.visibility = View.GONE
        binding?.imgquit?.visibility = View.GONE

        binding?.linmain?.visibility = View.VISIBLE



    }

    override fun onCardAppeared(view: View?, position: Int) {
        binding?.imglike?.visibility = View.GONE
        binding?.imgquit?.visibility = View.GONE
        binding?.linmain?.visibility = View.VISIBLE


    }

    override fun onCardDisappeared(view: View?, position: Int) {
        binding?.imglike?.visibility = View.GONE
        binding?.imgquit?.visibility = View.GONE



    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.linfilter-> showDialog()
//            R.id.linsuperlike->{
//                val setting = SwipeAnimationSetting.Builder()
//                    .setDirection(Direction.Top)
//                    .setDuration(Duration.Slow.duration)
//                    .setInterpolator(AccelerateInterpolator())
//                    .build()
//                manager?.setSwipeAnimationSetting(setting)
//                binding?.cardStackView?.swipe()
//            }
            R.id.linlike->{
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Slow.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                manager?.setSwipeAnimationSetting(setting)
                binding?.cardStackView?.swipe()
            }
            R.id.linexit->{
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Slow.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                manager?.setSwipeAnimationSetting(setting)
                binding?.cardStackView?.swipe()
            }
            R.id.linsendmessage->{
                showMessageDialog()
            }

        }
    }
    private fun showMessageDialog() {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.messagedialog)
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
       val  imgsend = dialog.findViewById<View>(R.id.imgsend) as ImageView
        imgsend.setOnClickListener{
            dialog?.dismiss()
        }



        dialog.show()

    }


    private fun showDialog() {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.filterdialog)
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
        spinnerdistance = dialog.findViewById<View>(R.id.spinnerdistance) as Spinner
        tvmale = dialog.findViewById<View>(R.id.tvmale) as TextView
        tvfemale = dialog.findViewById<View>(R.id.tvfemale) as TextView
        tvshemale = dialog.findViewById<View>(R.id.tvshemale) as TextView
       val  imgback = dialog.findViewById<View>(R.id.imgback) as ImageView
        spinner()
        imgback.setOnClickListener{
            dialog?.dismiss()
        }

        tvmale?.setOnClickListener{
            tvmale?.setBackgroundDrawable(resources.getDrawable(R.drawable.roundcorner2))
            tvshemale?.setBackgroundDrawable(null)
            tvfemale?.setBackgroundDrawable(resources.getDrawable(R.drawable.zeroroundcornerstrokewhite))
            tvmale?.setTextColor(resources.getColor(R.color.white))
            tvfemale?.setTextColor(resources.getColor(R.color.black))
            tvshemale?.setTextColor(resources.getColor(R.color.black))
        }
        tvfemale?.setOnClickListener{
            tvmale?.setBackgroundDrawable(null)
            tvfemale?.setBackgroundDrawable(resources.getDrawable(R.drawable.roundcorner2))
            tvshemale?.setBackgroundDrawable(null)
            tvmale?.setTextColor(resources.getColor(R.color.black))
            tvfemale?.setTextColor(resources.getColor(R.color.white))
            tvshemale?.setTextColor(resources.getColor(R.color.black))
        }
        tvshemale?.setOnClickListener{
            tvmale?.setBackgroundDrawable(null)
            tvshemale?.setBackgroundDrawable(resources.getDrawable(R.drawable.roundcorner2))
            tvfemale?.setBackgroundDrawable(resources.getDrawable(R.drawable.zeroroundcornerstrokewhite))
            tvmale?.setTextColor(resources.getColor(R.color.black))
            tvfemale?.setTextColor(resources.getColor(R.color.black))
            tvshemale?.setTextColor(resources.getColor(R.color.white))
        }

        dialog.show()

    }
    private fun spinner(){
        val languages = resources.getStringArray(R.array.distancerange)
        val adapter = ArrayAdapter(activity as FragmentActivity,
            R.layout.simple_spinner_item,R.id.tvspinner, languages)
        spinnerdistance?.adapter = adapter
        spinnerdistance?.setPrompt("Select your favorite Planet!");
        spinnerdistance?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                var  selected = spinnerdistance?.getSelectedItem().toString();
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

    override fun getdata(activity: FragmentActivity, data: List<Data>) {
        this.data = data
        adapter =CardStackAdapter(requireActivity(), data)
        binding?.cardStackView?.layoutManager = manager

        binding?.cardStackView?.adapter = adapter
        binding?.cardStackView?.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

    }
    private fun reload() {
        val old = adapter?.getSpots()
        val new = createSpots()
        val callback = SpotDiffCallback(old!!, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter?.setSpots(new)
        result.dispatchUpdatesTo(adapter!!)
    }
    private fun createSpots(): java.util.ArrayList<Data> {
        val spots = java.util.ArrayList<Data>()

        return spots
    }


}