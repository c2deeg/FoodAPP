package com.app.marier.fragments.LocationFragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.location.LocationListener
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.marier.R
import com.app.marier.Utils.GPSTracker
import com.app.marier.databinding.FragmentAddPhotosBinding
import com.app.marier.databinding.FragmentLocationBinding
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest
import java.io.IOException
import java.util.*

class LocationFragment : Fragment(), LocationListener, View.OnClickListener {
    val REQUEST_LOCATION_PERMISSION =100

    private lateinit var locationManager: LocationManager
    private var binding:FragmentLocationBinding?=null

    private var gpsTracker: GPSTracker? = null
    private var handler: Handler? = null
    private var latitude = 0.0
    private var longitude = 0.0
    lateinit var lng:String
    lateinit var lat:String
    private val AUTOCOMPLETE_REQUEST_CODE = 1


    private var fusedLocationProvider: FusedLocationProviderClient? = null
    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = 30
        fastestInterval = 10
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        maxWaitTime = 60
    }

    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val locationList = locationResult.locations
            if (locationList.isNotEmpty()) {
                //The last location in the list is the newest
                val location = locationList.last()
                Toast.makeText(
                    activity,
                    "Got Location: " + location.toString(),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        } else {
            // Location permissions already granted
        }
        listeners()
        handler = Handler()

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(activity as FragmentActivity)

        checkLocationPermission()
        handler!!.postDelayed({
            gpsTracker = GPSTracker(activity)
            if (gpsTracker!!.canGetLocation()) {
                gpsTracker!!.location
                latitude = gpsTracker!!.latitude
                longitude = gpsTracker!!.longitude
            }
//            progressBarNearMe!!.progress
            lng = longitude.toString()
            lat = latitude.toString()
//            progressBarNearMe.visibility = View.GONE
//            rv_nearMe.setVisibility(View.VISIBLE)
//            homePresenter.nearMeHotelsMethod(lng, lat)
        }, 500)


        val gcd = Geocoder(requireActivity(), Locale.getDefault())
//        progressBarLocation.setVisibility(View.VISIBLE)
//        img_location.setVisibility(View.GONE)
        handler!!.postDelayed({
            try {
                var addresses: List<Address>? = null
                addresses = gcd.getFromLocation(latitude, longitude, 1)
                Toast.makeText(activity, latitude.toString(), Toast.LENGTH_SHORT).show()

                val finalAddresses = addresses
//                img_location.setVisibility(View.VISIBLE)
//                progressBarLocation.setVisibility(View.GONE)
                if (finalAddresses!!.size > 0) {
                    val cityName = finalAddresses!![0].locality
//                    binding!!.tvcityname.setText(cityName)
                    Toast.makeText(requireActivity(), cityName, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "couldn't find location", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }, 900)



        return view
    }


    private fun listeners(){
        binding?.tvallowlocationaccess?.setOnClickListener(this)
    }



    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                activity as FragmentActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity as FragmentActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(activity as FragmentActivity)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        //Prompt the user once explanation has been shown
                        requestLocationPermission()
                    }
                    .create()
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                requestLocationPermission()
            }
        } else {
            checkBackgroundLocation()
        }
    }

    private fun checkBackgroundLocation() {
        if (ActivityCompat.checkSelfPermission(
                activity as FragmentActivity,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestBackgroundLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            activity as FragmentActivity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    }

    private fun requestBackgroundLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                activity as FragmentActivity,
                arrayOf(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION
            )
        } else {
            ActivityCompat.requestPermissions(
                activity as FragmentActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            activity as FragmentActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        fusedLocationProvider?.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            Looper.getMainLooper()
                        )

                        // Now check background location
                        checkBackgroundLocation()
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(activity, "permission denied", Toast.LENGTH_LONG).show()

                    // Check if we are in a state where the user has denied the permission and
                    // selected Don't ask again
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(
                            activity as FragmentActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        startActivity(
                            Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", activity!!.packageName, null),
                            ),
                        )
                    }
                }
                return
            }
            MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            activity as FragmentActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        fusedLocationProvider?.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            Looper.getMainLooper()
                        )

                        Toast.makeText(
                            activity as FragmentActivity,
                            "Granted Background Location Permission",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(activity as FragmentActivity, "permission denied", Toast.LENGTH_LONG).show()
                }
                return

            }
        }
    }



    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
        private const val MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION = 66
    }

    override fun onLocationChanged(p0: Location) {

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvallowlocationaccess->{
                findNavController().navigate(R.id.action_locationFragment_to_sexualOrientationFragment)
            }
        }

    }


}