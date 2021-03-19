package com.example.myevents.fragments

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myevents.*
import com.example.myevents.R
import com.example.myevents.activity.EventActivity
import com.example.myevents.data.Events
import com.example.myevents.databinding.FragmentEventsBinding
import com.example.myevents.viewmodels.EventViewModel
import com.google.android.gms.location.*


class EventsFragment : Fragment(), EventClickListener {

    private val viewModel: EventViewModel by lazy {
        ViewModelProvider(this).get(EventViewModel::class.java)
    }

    private lateinit var binding: FragmentEventsBinding

    lateinit var adapter: EventAdapter

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    val PERMISSION_ID = 606

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        activity?.title = getString(R.string.title_home)


        binding.refreshLayout.setOnRefreshListener {
            setupRecyclerView()
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        setupRecyclerView()
//        requestPermission()
//        getLastLocation()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.refreshLayout.isRefreshing = true

        viewModel.getEonetEvents()!!.observe(viewLifecycleOwner, Observer { listOfEvents ->
            adapter = EventAdapter(listOfEvents.events, this@EventsFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        })
        binding.refreshLayout.isRefreshing = false
    }


    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    fun requestPermission(){
        requestPermissions(
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


    fun isLocationEnabled():Boolean{
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        var locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    fun getLastLocation(){
        if(checkPermission()){
            if(isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {task->
                    var location: Location? = task.result
                    if(location == null){
                        //NewLocationData()
                    }else{
                        Log.v("Location:" ,"Your Location:"+ location.longitude)
                        //textView.text = "You Current Location is : Long: "+ location.longitude + " , Lat: " + location.latitude
                    }
                }
            }else{
                Toast.makeText(context,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
            }
        }else{
            requestPermission()
        }
    }

//    fun NewLocationData(){
//        var locationRequest =  LocationRequest()
//        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        locationRequest.interval = 0
//        locationRequest.fastestInterval = 0
//        locationRequest.numUpdates = 1
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
//        fusedLocationProviderClient!!.requestLocationUpdates(
//            locationRequest,locationCallback, Looper.myLooper()
//        )
//    }


    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.v("Location:","your last last location: "+ lastLocation.longitude.toString())
                    //textView.text = "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(lastLocation.latitude,lastLocation.longitude)
        }
    }


    override fun onItemClick(event: Events) {
        Toast.makeText(context, "Event ${event.title}", Toast.LENGTH_LONG).show()

        val showEventIntent = Intent(context, EventActivity::class.java)
        showEventIntent.putExtra("event_title", event.title)
        context?.startActivity(showEventIntent)

    }

    companion object {
        val TAG: String = EventsFragment::class.java.simpleName
        fun newInstance() = EventsFragment()
    }

}