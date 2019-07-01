package com.cuidadoanimal.petcare.gui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cuidadoanimal.petcare.R
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
import java.io.IOException

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onMarkerClick(p0: Marker?): Boolean = false

    private var map: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location //Obtiene la última localización del cliente
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1 // Any number
        private const val REQUEST_CHECK_SETTINGS = 2
        private const val PLACE_PICKER_REQUEST = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment = SupportMapFragment.newInstance()

        val fm: FragmentManager? = fragmentManager
        val ft: FragmentTransaction? = fm?.beginTransaction()

        ft?.replace(R.id.fl_map, mapFragment)?.commit()

        mapFragment?.getMapAsync(this)

        fusedLocationClient = activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                placeMarker(LatLng(lastLocation.latitude, lastLocation.longitude))
            }
        }
        createLocationRequest()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fr_fab.setOnClickListener {
            loadPlacePicker()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the firebaseUser will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the firebaseUser has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map?.setOnMarkerClickListener(this) //Dicha actividad se encargará de los mapas
        map?.uiSettings?.isZoomControlsEnabled = true //Habilitando Zoom
        setUpMap()
    }

    /*Función de chequeo de permisos especifico*/
    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(
                this.context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
            return
        }

        map?.isMyLocationEnabled = true //Desplegar nuestra localización
        map?.mapType = GoogleMap.MAP_TYPE_TERRAIN

        activity?.let {
            fusedLocationClient.lastLocation.addOnSuccessListener(it) { location ->

                if (location != null) {

                    lastLocation = location
                    val currentLatLong = LatLng(location.latitude, location.longitude)
                    placeMarker(currentLatLong)
                    map?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 15f))

                }
            }
        }
    }

    /*Método para el icono rojo de úbicación*/
    private fun placeMarker(location: LatLng) {

        val markerOption = MarkerOptions().position(location)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))

        val titleStr = getAddress(location)
        markerOption.title(titleStr)
        map?.addMarker(markerOption)
    }

    /*Función que convierte una coordenada en dirección*/
    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(this.context!!)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && addresses.isNotEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapsFragment", e.localizedMessage)
        }

        return addressText
    }

    private fun startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(
                this.context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
            return
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    /*Función de actualizar ubicacion de usuario*/
    private fun createLocationRequest() {
        locationRequest = LocationRequest()

        locationRequest.interval = 10000

        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client = activity?.let { LocationServices.getSettingsClient(it) }
        val task = client?.checkLocationSettings(builder.build())

        task?.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task?.addOnFailureListener { e ->

            if (e is ResolvableApiException) {

                try {
                    e.startResolutionForResult(
                        activity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {

                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }
        /*Aqui obtiene los detalles sobre el lugar seleccionado si tiene un resultado
        * y luego coloca un marcador en esa posición en el mapa*/
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this.context!!, data)
                var addressText = place.name.toString()
                addressText += "\n" + place.address.toString()

                placeMarker(place.latLng)
            }
        }
    }


    /*Place Search*/
    /*Inicia la interfaz de usuario del selector de lugares y luego
    * inicia la intencion del selector de lugares*/
    private fun loadPlacePicker() {
        val builder = PlacePicker.IntentBuilder()

        try {
            startActivityForResult(
                builder.build(activity),
                PLACE_PICKER_REQUEST
            )
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }
}
